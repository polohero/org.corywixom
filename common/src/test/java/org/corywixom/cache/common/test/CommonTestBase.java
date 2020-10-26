package org.corywixom.cache.common.test;

import java.util.Date;
import java.util.UUID;

import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.core.test.CoreTestBase;
import org.corywixom.cache.core.test.RandomIntUtil;

public class CommonTestBase extends CoreTestBase {

    public CacheItem generateCacheItem(){

        long ttl = RandomIntUtil.nextInt(9999999);
        Date expire = new Date(new Date().getTime() + ttl);

        return new CacheItem(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            new Date(),
            ttl,
            expire
        );
    }

    public CacheItem generateCacheItem(long ttlSeconds){

        Date expire = new Date(new Date().getTime() + (ttlSeconds * 1000));

        return new CacheItem(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            new Date(),
            ttlSeconds,
            expire
        );
    }

}
