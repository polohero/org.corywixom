package org.corywixom.cache.restws.service;

import org.corywixom.cache.common.model.CacheItemDTO;
import org.springframework.http.ResponseEntity;

public interface ICacheService {

    ResponseEntity<CacheItemDTO> get(String key);

    ResponseEntity<CacheItemDTO> delete(String key);

    ResponseEntity<Void> put(String key, String value, String ttl, String timeUnit);
}
