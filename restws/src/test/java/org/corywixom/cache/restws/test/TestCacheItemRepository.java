package org.corywixom.cache.restws.test;

import java.util.HashMap;

import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.data.repository.ICacheItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCacheItemRepository implements ICacheItemRepository {
    private static final Logger LOG = LoggerFactory.getLogger(TestCacheItemRepository.class);
    private static HashMap<String, CacheItem> _cache = new HashMap<>();

    public TestCacheItemRepository(){
    }

    @Override
    public CacheItem get(String key) {
        LOG.info("get");
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
