package com.belajarservlete.servlet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
