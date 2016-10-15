package com.yugi.service;

import com.yugi.pojo.Storable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/25.
 */
public interface RedisService {

    public List<String> get(List<String> keys);

    public Map<String, String> getMap(List<String> keys);

    public boolean exists(String key);

    public String get(String key);

    public void del(String key);

    public void set(String key, String value);

    public void set(String key, String value, int seconds);

    public void expire(String key, int seconds);

    public void rpush(String key, String... values);

    public Map<String, String> blpop(String... keys);

    Long hincr(String key, String field, long increment);

    Map<String, String> hgetAll(String key);

    void store(String key,Storable store);

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    <T> T get(String key, Class<T> clazz);
}
