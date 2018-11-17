package com.jiuxian.common.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class JSONUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String toJSON(T object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return mapper.readerFor(clazz).readValue(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <E> List<E> jsonToList(String json, Class<E> clazz) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <K, V> Map<K, V> jsonToMap(String json, Map<K, V> map) {
        MapType mapType = mapper.getTypeFactory().constructRawMapType(Map.class);
        try {
            return mapper.readValue(json, mapType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
