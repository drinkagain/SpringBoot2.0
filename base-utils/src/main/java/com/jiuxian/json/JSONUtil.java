package com.jiuxian.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.jiuxian.http.IpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static <T> String toJSON(T object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            return mapper.readerFor(clazz).readValue(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <E> List<E> toList(String json, Class<E> clazz) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <K, V> Map<K, V> toMap(String json) {
        MapType mapType = mapper.getTypeFactory().constructRawMapType(Map.class);
        try {
            return mapper.readValue(json, mapType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <K, V> Map<K, V> toMap(String s, Class<K> keyClass, Class<V> valueClass) {
        try {
            return mapper.readValue(s, mapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {

        List<String> list = toList("[1,2,3,4,5]", String.class);
        list.forEach(item -> {
            System.out.println(item + 1);
        });
    }
}