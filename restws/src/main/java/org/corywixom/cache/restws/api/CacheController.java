package org.corywixom.cache.restws.api;

import org.corywixom.cache.common.model.CacheItemDTO;
import org.corywixom.cache.restws.service.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class CacheController {
    private static final Logger LOG = LoggerFactory.getLogger(CacheController.class);
    private ICacheService _service;

    public CacheController(ICacheService service){
        _service = service;
    }

    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<CacheItemDTO> getCache(
        @RequestParam("key") String key){
        return _service.get(key);
    }

    @PostMapping(value = "",
        produces = {"application/json"},
        consumes = {"text/plain"})
    public ResponseEntity<Void> postCache(
        @RequestParam("key") String key,
        @RequestBody String value,
        @RequestParam("ttl") String ttl,
        @RequestParam("timeUnit") String timeUnit){
        return _service.put(key, value, ttl, timeUnit);
    }

    @DeleteMapping(value = "", produces = {"application/json"})
    public ResponseEntity<CacheItemDTO> deleteCache(
        @RequestParam("key") String key){
        return _service.delete(key);
    }
}
