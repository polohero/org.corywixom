package org.corywixom.cache.restws.test;

import java.util.concurrent.TimeUnit;
import org.corywixom.cache.business.cache.ICache;
import org.corywixom.cache.business.mapping.CacheItemMapper;
import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.core.exception.BadRequestException;
import org.corywixom.cache.core.exception.InternalServerErrorException;
import org.corywixom.cache.core.exception.NotFoundException;
import org.corywixom.cache.restws.config.CacheServiceConfig;
import org.corywixom.cache.restws.service.CacheService;
import org.corywixom.cache.restws.service.ICacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class TestCacheServiceErrors extends RestTestBase{

    @Autowired
    private ICacheService _service;

    @Test(expected = BadRequestException.class)
    public void testBadRequest_GetNull(){
        _service.get(null);
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_DeleteNull(){
        _service.delete(null);
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_PutNull(){
        _service.put(null, null, null, null);
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_PutNullTTL(){
        _service.put("AAAA", "AAAA", null, "seconds");
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_PutInvalidTTL(){
        _service.put("AAAA", "AAAA", "seconds", "seconds");
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_PutNullTimeUnit(){
        _service.put("AAAA", "AAAA", "99", null);
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_PutInvalidTimeUnit(){
        _service.put("AAAA", "AAAA", "99", "bad");
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_PutTooLarge(){
        _service.put(null, null, null, null);
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequest_GetEmpty(){
        _service.get("");
    }

    @Test(expected = NotFoundException.class)
    public void testNotFound(){
        _service.get("NOT FOUND");
    }

    @Test(expected = InternalServerErrorException.class)
    public void testInternalServerException_Get(){

        ICache errorCache =
            new ICache() {
                @Override
                public CacheItem get(String key) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public CacheItem delete(String key) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(CacheItem cacheItem) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(String key, String value, long ttlseconds) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(String key, String value, long ttl, TimeUnit timeUnit) {
                    throw new RuntimeException("ERROR");
                }
            };

        ICacheService cacheService =
            new CacheService(
                errorCache,
                new CacheItemMapper(),
                new CacheServiceConfig()
            );

        cacheService.get("NOT FOUND");
    }

    @Test(expected = InternalServerErrorException.class)
    public void testInternalServerException_Delete(){

        ICache errorCache =
            new ICache() {
                @Override
                public CacheItem get(String key) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public CacheItem delete(String key) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(CacheItem cacheItem) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(String key, String value, long ttlseconds) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(String key, String value, long ttl, TimeUnit timeUnit) {
                    throw new RuntimeException("ERROR");
                }
            };

        ICacheService cacheService =
            new CacheService(
                errorCache,
                new CacheItemMapper(),
                new CacheServiceConfig()
            );

        cacheService.delete("NOT FOUND");
    }

    @Test(expected = InternalServerErrorException.class)
    public void testInternalServerException_Put(){

        ICache errorCache =
            new ICache() {
                @Override
                public CacheItem get(String key) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public CacheItem delete(String key) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(CacheItem cacheItem) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(String key, String value, long ttlseconds) {
                    throw new RuntimeException("ERROR");
                }

                @Override
                public void put(String key, String value, long ttl, TimeUnit timeUnit) {
                    throw new RuntimeException("ERROR");
                }
            };

        ICacheService cacheService =
            new CacheService(
                errorCache,
                new CacheItemMapper(),
                new CacheServiceConfig()
            );

        cacheService.put("ASD", "AAA", "99", "SECONDS");
    }
}
