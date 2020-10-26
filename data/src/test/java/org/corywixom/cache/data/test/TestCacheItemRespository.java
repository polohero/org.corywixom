package org.corywixom.cache.data.test;

import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.common.test.CommonTestBase;
import org.corywixom.cache.core.utilities.ObjectUtilities;
import org.corywixom.cache.data.repository.ICacheItemRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class TestCacheItemRespository extends CommonTestBase {

    @Autowired
    private ICacheItemRepository _repository;

    @Test
    @Ignore
    public void test_GetAndPut(){

        CacheItem cacheItem = generateCacheItem();

        _repository.put(cacheItem);

        CacheItem current =
            _repository.get(cacheItem.getKey());

        assertIt(
            cacheItem.equals(current),
            cacheItem.toString(),
            current.toString(),
            "Should be the same."
        );

    }

    @Test
    @Ignore
    public void test_Delete(){

        CacheItem cacheItem = generateCacheItem();

        _repository.put(cacheItem);

        CacheItem current =
            _repository.delete(cacheItem.getKey());

        assertIt(
            cacheItem.equals(current),
            cacheItem.toString(),
            current.toString(),
            "Should be the same."
        );

        current = _repository.get(cacheItem.getKey());

        assertIt(
            null == current,
            ObjectUtilities.toPrintString(current),
            "NULL",
            "Should be null now."
        );
    }

}
