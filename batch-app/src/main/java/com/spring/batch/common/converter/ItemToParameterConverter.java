package com.spring.batch.common.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

public class ItemToParameterConverter {

    public static Map<String, Object> mapConvert(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(o, Map.class);
    }

    public static Supplier<Map<String, Object>> mapSupplier(Object o) {
        return () -> Collections.unmodifiableMap(mapConvert(o));
    }
}
