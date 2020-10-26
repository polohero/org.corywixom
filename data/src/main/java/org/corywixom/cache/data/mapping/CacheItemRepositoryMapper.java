package org.corywixom.cache.data.mapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

import org.corywixom.cache.core.base.AbstractMapper;
import org.corywixom.cache.common.entities.CacheItem;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Component
public class CacheItemRepositoryMapper
    extends AbstractMapper<CacheItem, Map<String, AttributeValue>>
    implements ICacheItemRepositoryMapper{

    private static final String KEY = "key";
    private static final String VALUE = "value";
    private static final String INSERT = "insert";
    private static final String TTL = "ttl";
    private static final String EXPIRE = "expire";

    @Override
    public Map<String, AttributeValue> toDTO(CacheItem cacheItem) {
        Map<String, AttributeValue> map = new HashMap<>(5);

        map.put(KEY, AttributeValue.builder().s(cacheItem.getKey()).build());
        if(null != cacheItem.getValue()){
            map.put(
                VALUE,
                AttributeValue.builder().s(
                    cacheItem.getValue()).build());
        }
        if(null != cacheItem.getInsert()){
            map.put(
                INSERT,
                    AttributeValue.builder().n(
                        new Long(cacheItem.getInsert().getTime()).toString()).build());
        }
        map.put(
            TTL,
            AttributeValue.builder().n(
                new Long(cacheItem.getTtl()).toString()).build());
        map.put(
            EXPIRE,
            AttributeValue.builder().n(
                new Long(cacheItem.getExpire().getTime()/ 1000).toString()).build());

        return map;
    }

    @Override
    public CacheItem toEntity(Map<String, AttributeValue> map) {

        return new CacheItem(
            map.get(KEY).s(),
            map.get(VALUE).s(),
            map.containsKey(INSERT)
                ? new Date(new Long(map.get(INSERT).n()))
                : null,
            new Long(map.get(TTL).n()),
            map.containsKey(EXPIRE)
                ? new Date(new Long(map.get(EXPIRE).n()) * 1000)
                : null
        );

    }
}
