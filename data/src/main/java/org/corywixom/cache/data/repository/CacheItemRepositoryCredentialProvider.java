package org.corywixom.cache.data.repository;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

@Component
public class CacheItemRepositoryCredentialProvider implements ICacheItemRepositoryCredentialProvider{

    private ICacheItemRepositoryConfig _config;

    public CacheItemRepositoryCredentialProvider(
        ICacheItemRepositoryConfig config
    ){
        _config = config;
    }

    @Override
    public AwsCredentials resolveCredentials() {
        return new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return _config.accessKeyId();
            }

            @Override
            public String secretAccessKey() {
                return _config.secretAccessKey();
            }
        };
    }

}
