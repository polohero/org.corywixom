package org.corywixom.cache.restws.service;

import java.util.concurrent.TimeUnit;

import org.corywixom.cache.business.cache.ICache;
import org.corywixom.cache.business.mapping.ICacheItemMapper;
import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.common.model.CacheItemDTO;
import org.corywixom.cache.core.exception.BadRequestException;
import org.corywixom.cache.core.exception.InternalServerErrorException;
import org.corywixom.cache.core.exception.NotFoundException;
import org.corywixom.cache.core.utilities.ObjectUtilities;
import org.corywixom.cache.core.utilities.StringUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CacheService implements ICacheService {
    private static final Logger LOG = LoggerFactory.getLogger(CacheService.class);
    private ICache _cache;
    private ICacheItemMapper _mapper;
    private ICacheServiceConfig _config;

    public CacheService(
        ICache cache,
        ICacheItemMapper mapper,
        ICacheServiceConfig config){
        _cache = cache;
        _mapper = mapper;
        _config = config;
    }

    @Override
    public ResponseEntity<CacheItemDTO> get(String key) {
        try{
            if(StringUtilities.isNullOrWhitespace(key)){
                throw new BadRequestException(
                    "The KEY cannot be null. Received: " +
                        ObjectUtilities.toPrintString(key)
                );
            }

            CacheItem cacheItem = _cache.get(key);

            if(null == cacheItem){
                throw new NotFoundException(
                    "The KEY was not found. Received: " +
                        ObjectUtilities.toPrintString(key)
                );
            }

            return ResponseEntity.ok(_mapper.toDTO(cacheItem));
        }
        catch (NotFoundException notFound){
            LOG.info(notFound.getMessage());
            throw notFound;
        }
        catch (BadRequestException badRequest){
            LOG.warn(badRequest.getMessage());
            throw badRequest;
        }
        catch (Exception exception){
            LOG.error(
                "An unknown error occurred calling get(String key). KEY: " +
                    ObjectUtilities.toPrintString(key)
            );

            throw new InternalServerErrorException(
                "An unknown error occurred"
            );
        }
    }

    @Override
    public ResponseEntity<CacheItemDTO> delete(String key) {
        try{
            if(StringUtilities.isNullOrWhitespace(key)){
                throw new BadRequestException(
                    "The KEY cannot be null. Received: " +
                        ObjectUtilities.toPrintString(key)
                );
            }

            CacheItem cacheItem = _cache.delete(key);

            return ResponseEntity.ok(
                null == cacheItem
                    ? null
                    : _mapper.toDTO(cacheItem));
        }
        catch (BadRequestException badRequest){
            LOG.warn(badRequest.getMessage());
            throw badRequest;
        }
        catch (Exception exception){
            LOG.error(
                "An unknown error occurred calling delete(String key). KEY: " +
                    ObjectUtilities.toPrintString(key)
            );

            throw new InternalServerErrorException(
                "An unknown error occurred"
            );
        }
    }

    @Override
    public ResponseEntity<Void> put(String key, String value, String ttl, String timeUnit) {
        try{
            if(StringUtilities.isNullOrWhitespace(key)){
                throw new BadRequestException(
                    "The KEY cannot be null. Received: " +
                        ObjectUtilities.toPrintString(key)
                );
            }

            putInternal(key, value, ttl, timeUnit);

            return ResponseEntity.ok().build();
        }
        catch (BadRequestException badRequest){
            LOG.warn(badRequest.getMessage());
            throw badRequest;
        }
        catch (Exception exception){
            LOG.error(
                "An unknown error occurred calling put(String key, String value, String ttl, String timeUnit). " +
                    "key: " + ObjectUtilities.toPrintString(key) + " " +
                    "value: " + ObjectUtilities.toPrintString(value) + " " +
                    "ttl: " + ObjectUtilities.toPrintString(ttl) + " " +
                    "timeUnit: " + ObjectUtilities.toPrintString(timeUnit)
            );

            throw new InternalServerErrorException(
                "An unknown error occurred"
            );
        }
    }

    private void putInternal(
        String key,
        String value,
        String ttl,
        String timeUnit){

        if(StringUtilities.isNullOrWhitespace(key)){
            throw new BadRequestException(
                "The KEY cannot be null. Received: " +
                    ObjectUtilities.toPrintString(key)
            );
        }

        if(StringUtilities.isNullOrWhitespace(value)){
            throw new BadRequestException(
                "The VALUE cannot be null. Received: " +
                    ObjectUtilities.toPrintString(value)
            );
        }

        TimeUnit unit = parseTimeUnit(timeUnit);
        long longTtl = parseTTL(ttl);

        if(unit.toSeconds(longTtl) > _config.maxTTLSeconds()){
            throw new BadRequestException(
                "The TTL is bigger than the max value." +
                    " Received: " + ObjectUtilities.toPrintString(ttl) + " " + unit +
                    " MaxValue: " + ObjectUtilities.toPrintString(_config.maxTTLSeconds()) + " seconds."
            );
        }

        _cache.put(
            key,
            value,
            longTtl,
            unit
        );
    }

    private TimeUnit parseTimeUnit(String timeUnit){

        if(null == timeUnit){
            throw new BadRequestException(
                "timeUnit is null. It must be specified for the ttl to be set for the cached data."
            );
        }

        try{
            return TimeUnit.valueOf(timeUnit.toUpperCase());
        }
        catch (IllegalArgumentException exception){
            LOG.warn(
                "The client passed bad data for the timeUnit. " +
                    "It could not be parsed, valid values are " +
                     "milliseconds, seconds, minutes, hours, or days." +
                    " Received: " + ObjectUtilities.toPrintString(timeUnit),
                exception);

            throw new BadRequestException(
                "The timeUnit not be parsed, valid values are " +
                    "milliseconds, seconds, minutes, hours, or days." +
                    " Received: " + ObjectUtilities.toPrintString(timeUnit)
            );
        }
    }

    private long parseTTL(String ttl){
        try{

            if(null == ttl){
                throw new BadRequestException(
                    "TTL is null. It must be specified for the cache to be valid."
                );
            }

            return Long.parseLong(ttl);
        }
        catch (NumberFormatException exception){
            LOG.warn(
                "The client passed bad data for TTL. It could not be parsed to a Long value." +
                    " Received: " + ObjectUtilities.toPrintString(ttl),
                exception);

            throw new BadRequestException(
                "TTL could not be parsed to a Long value." +
                 " Received: " + ObjectUtilities.toPrintString(ttl)
            );
        }
    }
}
