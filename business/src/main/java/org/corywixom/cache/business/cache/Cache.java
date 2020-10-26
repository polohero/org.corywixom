package org.corywixom.cache.business.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.data.repository.ICacheItemRepository;
import org.springframework.stereotype.Component;

@Component
public class Cache implements ICache {

    private ICacheTtl _ttl;
    private ICacheItemRepository _cacheItemRepository;

    public Cache(ICacheTtl ttl,
        ICacheItemRepository cacheItemRepository){
        _ttl = ttl;
        _cacheItemRepository = cacheItemRepository;
    }

    @Override
    public CacheItem get(String key) {
        return _cacheItemRepository.get(key);
    }

    @Override
    public CacheItem delete(String key) {
        return _cacheItemRepository.delete(key);
    }

    @Override
    public void put(CacheItem cacheItem) {
        _cacheItemRepository.put(cacheItem);
    }

    @Override
    public void put(String key, String value, long ttlseconds) {
        put(new CacheItem(
            key,
            value,
            new Date(),
            ttlseconds,
            _ttl.findExpiration(ttlseconds)
        ));
    }

    @Override
    public void put(String key, String value, long ttl, TimeUnit timeUnit) {
        put(new CacheItem(
            key,
            value,
            new Date(),
            _ttl.findSeconds(ttl, timeUnit),
            _ttl.findExpiration(new Date(), ttl, timeUnit)
        ));
    }
}
