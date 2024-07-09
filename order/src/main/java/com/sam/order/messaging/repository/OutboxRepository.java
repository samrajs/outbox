package com.sam.order.messaging.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sam.order.messaging.entity.OutboxEntity;
import com.sam.order.messaging.telemetry.MapTracingSetter;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxRepository {
    private final OutboxJpaRepository repository;
    private final OpenTelemetry openTelemetry;
    @WithSpan("outbox-write")
    public void save(OutboxEntity entity) {
        entity.setTracingSpanContext(getTrace());
        repository.save(entity);
    }

    private String getTrace() {
        TextMapPropagator propagator = openTelemetry.getPropagators().getTextMapPropagator();

        Map<String, Object> map = new HashMap<String, Object>();

        log.info( "Context : {}", Context.current() );

        propagator.inject(Context.current(), map, new MapTracingSetter());

        log.info( "Tracing context : {}", map.get("tracingspancontext") );

        return (String) map.get("tracingspancontext");
    }
}
