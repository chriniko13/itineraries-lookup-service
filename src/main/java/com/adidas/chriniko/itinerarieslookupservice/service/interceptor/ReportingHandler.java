package com.adidas.chriniko.itinerarieslookupservice.service.interceptor;

import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import com.adidas.chriniko.itinerarieslookupservice.service.kafka.ReportProducer;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Log4j2

@Aspect
@Configuration
public class ReportingHandler {

    private final ReportProducer reportProducer;

    @Autowired
    public ReportingHandler(ReportProducer reportProducer) {
        this.reportProducer = reportProducer;
    }

    @Pointcut("@annotation(com.adidas.chriniko.itinerarieslookupservice.service.interceptor.Report)")
    protected void definedPointcut() {
    }

    @AfterReturning(
            pointcut = "definedPointcut() && args(input,allItinerariesInfo,allItinerariesInfoDetailed)",
            returning = "result",
            argNames = "joinPoint,result,input,allItinerariesInfo,allItinerariesInfoDetailed"
    )
    public void afterReturning(JoinPoint joinPoint,
                               ItineraryInfoResult result,
                               ItinerarySearchInfo input, boolean allItinerariesInfo, boolean allItinerariesInfoDetailed) {

        try {
            log.trace("ReportingHandler ---> input: {}", input);
            log.trace("ReportingHandler ---> result: {}", result);

            reportProducer.send(input, result);

        } catch (Exception error) {
            log.error("reporting handler could not send report message", error);
        }
    }

}
