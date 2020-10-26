package org.corywixom.cache.business.test;

import java.util.HashMap;

import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.data.repository.ICacheItemRepository;

public class TestCacheItemRepository implements ICacheItemRepository {
    private static HashMap<String, CacheItem> _cache = new HashMap<>();

    public TestCacheItemRepository(){
    }

    @Override
    public CacheItem get(String key) {
        return _cache.get(key);
    }

    @Override
    public CacheItem delete(String key) {
        return _cache.remove(key);
    }

    @Override
    public void put(CacheItem cacheItem) {
        _cache.put(cacheItem.getKey(), cacheItem);
    }
}
