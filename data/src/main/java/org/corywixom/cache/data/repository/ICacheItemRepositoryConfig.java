package org.corywixom.cache.data.repository;

public interface ICacheItemRepositoryConfig {
    String region();

    String tableName();

    String keyName();

    String accessKeyId();

    String secretAccessKey();
}
