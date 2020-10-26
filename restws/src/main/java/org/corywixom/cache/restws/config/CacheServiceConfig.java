package org.corywixom.cache.restws.config;

import org.corywixom.cache.data.repository.ICacheItemRepositoryConfig;
import org.corywixom.cache.restws.service.ICacheServiceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheServiceConfig implements ICacheServiceConfig,
                                           ICacheItemRepositoryConfig {
    @Value("${cache.maxTTLSeconds:99999999999999}")
    private Long maxTTLSeconds;
    @Override
    public long maxTTLSeconds() {
        return maxTTLSeconds;
    }

    @Value("${cache.name.regionName:cache.region}")
    private String regionName;
    @Override
    public String region() {
        return System.getProperty(regionName);
    }

    @Value("${cache.name.tableName:cache.tableName}")
    private String tableNameName;
    @Override
    public String tableName() {
        return System.getProperty(tableNameName);
    }


    @Value("${cache.name.key:cache.keyName}")
    private String keyNameName;
    @Override
    public String keyName() {
        return System.getProperty(keyNameName);
    }

    @Value("${cache.name.accessKeyId:cache.accessKeyId}")
    private String accessKeyIdName;
    @Override
    public String accessKeyId() {
        return System.getProperty(accessKeyIdName);
    }

    @Value("${cache.name.secretAccessKey:cache.secretAccessKey}")
    private String secretAccessKeyName;
    @Override
    public String secretAccessKey() {
        return System.getProperty(secretAccessKeyName);
    }
}
