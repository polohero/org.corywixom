package org.corywixom.cache.data.repository;

import org.corywixom.cache.common.entities.CacheItem;

public interface ICacheItemRepository {

    CacheItem get(String key);

    CacheItem delete(String key);

    void put(CacheItem cacheItem);

}
