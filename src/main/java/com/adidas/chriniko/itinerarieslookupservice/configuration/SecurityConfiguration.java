package com.adidas.chriniko.itinerarieslookupservice.configuration;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String REALM = "ITINERARIES_LOOKUP_SERVICE_REALM";

    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        String hashedPassword = sha512(password);
        auth.inMemoryAuthentication().withUser(username).password(hashedPassword).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").hasRole("ADMIN")
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return sha512(String.valueOf(rawPassword));
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return sha512(String.valueOf(rawPassword)).equals(encodedPassword);
            }
        };
    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    private static class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
        @Override
        public void commence(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final AuthenticationException authException) throws IOException {
            //Authentication failed, send error response.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

            PrintWriter writer = response.getWriter();
            writer.println("HTTP Status 401 : " + authException.getMessage());
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            setRealmName(REALM);
            super.afterPropertiesSet();
        }
    }

    private String sha512(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encode(hash));
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
