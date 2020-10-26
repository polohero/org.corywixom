package org.corywixom.cache.business.mapping;

import org.corywixom.cache.core.base.AbstractMapper;
import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.common.model.CacheItemDTO;
import org.springframework.stereotype.Component;

@Component
public class CacheItemMapper
    extends AbstractMapper<CacheItem, CacheItemDTO>
    implements ICacheItemMapper {
    @Override
    public CacheItemDTO toDTO(CacheItem cacheItem) {
        return new CacheItemDTO(
            cacheItem.getKey(),
            cacheItem.getValue(),
            cacheItem.getInsert(),
            cacheItem.getTtl(),
            cacheItem.getExpire()
        );
    }

    @Override
    public CacheItem toEntity(CacheItemDTO dto) {
        return new CacheItem(
            dto.getKey(),
            dto.getValue(),
            dto.getInsert(),
            dto.getTtl(),
            dto.getExpire()
        );
    }
}
