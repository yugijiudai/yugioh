package com.yugi.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by Administrator on 2016/6/25.
 */
public class JsonUtil {
    private static final Logger logger = LogManager.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = ObjectMapperHolder.getObjectMapper();

    public static <T> T fromJsonQuietly(String json, Class<T> type) {
        try {
            return fromJson(json, type);
        } catch (Exception e) {
            logger.error("fromJson failed: {}", e.getMessage(), e);
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> type) {
        if (type == null || json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonQuietly(Object obj) {
        try {
            return toJson(obj);
        } catch (Exception e) {
            logger.error("toJson failed: {}", e.getMessage(), e);
        }
        return null;
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String json = mapper.writeValueAsString(obj);
            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class ObjectMapperHolder {
        public static ObjectMapper getObjectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_ANNOTATIONS);
            mapper.disable(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.disable(org.codehaus.jackson.map.DeserializationConfig.Feature.WRAP_EXCEPTIONS);
            mapper.disable(org.codehaus.jackson.map.SerializationConfig.Feature.USE_ANNOTATIONS);
            return mapper;
        }
    }
}
