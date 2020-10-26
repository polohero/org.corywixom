package org.corywixom.cache.business.test;

import org.corywixom.cache.business.cache.Cache;
import org.corywixom.cache.business.mapping.ICacheItemMapper;
import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.common.model.CacheItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class TestMapping extends BusinessTestBase{

    @Autowired
    private ICacheItemMapper _mapper;

    @Test
    public void testMappingToDTO(){
        CacheItem entity = generateCacheItem();

        CacheItemDTO dto = _mapper.toDTO(entity);

        assertIt(
            _mapper.compare(entity, dto),
            entity.toString(),
            dto.toString(),
            "Should be the same"
        );
    }

    @Test
    public void testMappingToEntity(){
        CacheItem entity = generateCacheItem();

        CacheItemDTO dto = _mapper.toDTO(entity);

        CacheItem entityTwo = _mapper.toEntity(dto);

        assertIt(
            entityTwo.equals(entity),
            entity.toString(),
            entityTwo.toString(),
            "Should be the same"
        );
    }

}
