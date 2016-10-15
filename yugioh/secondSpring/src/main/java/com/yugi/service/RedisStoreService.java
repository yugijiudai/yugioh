package com.yugi.service;

import com.yugi.pojo.Storable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/25.
 */
public interface RedisStoreService {

    void store(Storable store);

    void store(Storable store, Class<? extends Storable> targetClass);

    <T extends Storable> T get(Serializable key, Class<? extends Storable> targetClass);

    <T extends Storable> List<T> get(List<Serializable> keys, Class<? extends Storable> targetClass);

    void clear(Serializable storableId, Class<? extends Storable> targetClass);
}
