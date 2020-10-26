package org.corywixom.cache.data.mapping;

import java.util.Map;

import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.core.base.IMapper;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public interface ICacheItemRepositoryMapper extends IMapper<CacheItem, Map<String, AttributeValue>> {
}
