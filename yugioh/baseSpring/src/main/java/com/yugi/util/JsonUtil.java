package com.yugi.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.regexp.internal.RE;
import com.yugi.adapter.DoubleDefault0Adapter;
import com.yugi.adapter.IntegerDefault0Adapter;
import com.yugi.adapter.LongDefault0Adapter;
import com.yugi.annotation.GsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Created by Administrator on 2016/6/25.
 */
public class JsonUtil {
    private static final Logger logger = LogManager.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = ObjectMapperHolder.getObjectMapper();

    private static final ExclusionStrategy excludeStrategy = ExclusionStrategyHolder.getExclusionStrategy();

    public static <T> T fromJsonQuietly(String json, Class<T> type) {
        try {
            return fromJson(json, type);
        }
        catch (Exception e) {
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
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonQuietly(Object obj) {
        try {
            return toJson(obj);
        }
        catch (Exception e) {
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
        }
        catch (Exception e) {
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

    private static class ExclusionStrategyHolder {

        public static ExclusionStrategy getExclusionStrategy() {
            ExclusionStrategy excludeStrategy = new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    Collection<Annotation> annotations = f.getAnnotations();
                    for (Annotation annotation : annotations) {
                        Class<? extends Annotation> annotationType = annotation.annotationType();
                        if (annotationType.equals(GsonIgnore.class)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            };
            return excludeStrategy;
        }

    }


    /**
     * 把json的字符串转成对应的类型(可以是po,可以是List)
     *
     * @param json  要转的字符串
     * @param token 要转的类型
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, TypeToken<T> token) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .setExclusionStrategies(excludeStrategy)
                .create();
        try {
            return gson.fromJson(json, token.getType());
        }
        catch (Exception e) {
            System.err.println(json + " 无法转换为 " + token.getRawType().getName() + " 对象!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
