package com.adidas.chriniko.itinerarieslookupservice.client.connector;

import lombok.extern.log4j.Log4j2;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Log4j2

@Component
public class ConnectorRetryListener implements RetryListener {

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.debug("close operation --- throwable: {}", throwable.toString());
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.debug("onError operation --- throwable: {}", throwable.toString());
    }
}
