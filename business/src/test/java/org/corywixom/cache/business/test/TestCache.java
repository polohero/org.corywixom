package org.corywixom.cache.business.test;

import java.util.concurrent.TimeUnit;

import org.corywixom.cache.business.cache.Cache;
import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.core.utilities.ObjectUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class TestCache extends BusinessTestBase{

    @Autowired
    private Cache _cache;

    private static final String BAD_KEY = "BAD_KEY";

    @Test
    public void testCache_nullValueEmptyCache(){

        assertIt(
            null == _cache.get(BAD_KEY),
            ObjectUtilities.toPrintString(_cache.get(BAD_KEY)),
            "NULL",
            "Expected NULL value."
        );
    }

    @Test
    public void testCache_nullValueHasData(){

        _cache.put(generateCacheItem());

        assertIt(
            null == _cache.get(BAD_KEY),
            ObjectUtilities.toPrintString(_cache.get(BAD_KEY)),
            "NULL",
            "Expected NULL value."
        );
    }

    @Test
    public void testCache_putCacheItem(){

        CacheItem expected = generateCacheItem();

        _cache.put(expected);

        CacheItem current =_cache.get(expected.getKey());

        assertIt(
            expected.equals(current),
            ObjectUtilities.toPrintString(current),
            ObjectUtilities.toPrintString(expected),
            "Expected cache item to be cached."
        );
    }

    @Test
    public void testCache_putTTLS(){

        CacheItem expected = generateCacheItem();

        _cache.put(expected.getKey(), expected.getValue(), expected.getTtl());

        CacheItem current =_cache.get(expected.getKey());

        assertIt(
            expected.equals(current),
            ObjectUtilities.toPrintString(current),
            ObjectUtilities.toPrintString(expected),
            "Expected cache item to be cached."
        );
    }

    @Test
    public void testCache_putTTLM(){

        CacheItem expected = generateCacheItem();
        expected.setTtl(TimeUnit.MINUTES.toSeconds(99));

        _cache.put(expected.getKey(), expected.getValue(), 99, TimeUnit.MINUTES);

        CacheItem current =_cache.get(expected.getKey());


        assertIt(
            expected.equals(current),
            ObjectUtilities.toPrintString(current),
            ObjectUtilities.toPrintString(expected),
            "Expected cache item to be cached."
        );
    }

    @Test
    public void testCache_delete(){

        CacheItem expected = generateCacheItem();

        _cache.put(expected);

        CacheItem current =_cache.get(expected.getKey());

        assertIt(
            expected.equals(current),
            ObjectUtilities.toPrintString(current),
            ObjectUtilities.toPrintString(expected),
            "Expected cache item to be cached."
        );

        _cache.delete(expected.getKey());

        current =_cache.get(expected.getKey());

        assertIt(
            null == current,
            ObjectUtilities.toPrintString(current),
            "NULL",
            "Expected NULL value."
        );
    }
}
