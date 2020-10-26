package org.corywixom.cache.business.cache;

import java.util.concurrent.TimeUnit;

import org.corywixom.cache.common.entities.CacheItem;

public interface ICache {
    CacheItem get(String key);

    CacheItem delete(String key);

    void put(CacheItem cacheItem);

    void put(String key, String value, long ttlseconds);

    void put(String key, String value, long ttl, TimeUnit timeUnit);
}
