package com.sam.order.messaging.telemetry;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.sam.order.application.exception.OrderSystemException;

import io.opentelemetry.context.propagation.TextMapSetter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapTracingSetter implements TextMapSetter<Map<String, Object>>{
    private static final String TRACING_COLUMN = "tracingspancontext";

    @Override
    public void set(Map<String, Object> map, String key, String value) {
        log.debug( "Inside set of MapTracingSetter, key : {}, value : {}", key, value );
        Properties props = new Properties();
        props.put(key, value);
        String context = export( props );

        map = (map == null)?new HashMap<String, Object>():map;

        map.put(TRACING_COLUMN, context);
    }

    private String export(Properties props) {
        try (Writer sw = new StringWriter()) {
            props.store(sw, null);
            return sw.toString();
        }
        catch (IOException e) {
            throw new OrderSystemException("Error while converting trace context to String:", e);
        }
    }

}
