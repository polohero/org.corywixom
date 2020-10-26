package org.corywixom.cache.restws.test;

import java.util.concurrent.TimeUnit;

import org.corywixom.cache.business.mapping.ICacheItemMapper;
import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.common.model.CacheItemDTO;
import org.corywixom.cache.core.exception.NotFoundException;
import org.corywixom.cache.restws.service.ICacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class TestCacheService extends RestTestBase {

    @Autowired
    private ICacheService _service;

    @Autowired
    private ICacheItemMapper _mapper;

    @Test
    public void testGetCache(){

        CacheItem expected =
            generateCacheItem();

        _service.put(
            expected.getKey(),
            expected.getValue(),
            expected.getTtl() + "",
            TimeUnit.SECONDS.toString()
        );

        ResponseEntity<CacheItemDTO> response = _service.get(expected.getKey());

        assertIt(
            response.getStatusCode() == HttpStatus.OK,
            HttpStatus.OK + "",
            response.getStatusCode() + "",
            "Expected a 200 status"
        );

        CacheItemDTO current = response.getBody();

        assertIt(
            _mapper.compare(expected, current),
            current.toString(),
            expected.toString(),
            "Expected them to be the same."
        );
    }

    @Test
    public void testGetCache_LowerCase(){

        CacheItem expected =
            generateCacheItem();

        _service.put(
            expected.getKey(),
            expected.getValue(),
            expected.getTtl() + "",
            "seconds"
        );

        ResponseEntity<CacheItemDTO> response = _service.get(expected.getKey());

        assertIt(
            response.getStatusCode() == HttpStatus.OK,
            HttpStatus.OK + "",
            response.getStatusCode() + "",
            "Expected a 200 status"
        );

        CacheItemDTO current = response.getBody();

        assertIt(
            _mapper.compare(expected, current),
            current.toString(),
            expected.toString(),
            "Expected them to be the same."
        );
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteCache(){

        CacheItem expected =
            generateCacheItem();

        _service.put(
            expected.getKey(),
            expected.getValue(),
            expected.getTtl() + "",
            TimeUnit.SECONDS.toString()
        );

        ResponseEntity<CacheItemDTO> response = _service.get(expected.getKey());

        assertIt(
            response.getStatusCode() == HttpStatus.OK,
            HttpStatus.OK + "",
            response.getStatusCode() + "",
            "Expected a 200 status"
        );


        _service.delete(expected.getKey());

        _service.get(expected.getKey());
    }
}
