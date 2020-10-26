package org.corywixom.cache.data.test;

import org.corywixom.cache.data.mapping.ICacheItemRepositoryMapper;
import org.corywixom.cache.data.repository.CacheItemRepository;
import org.corywixom.cache.data.repository.ICacheItemRepository;
import org.corywixom.cache.data.repository.ICacheItemRepositoryConfig;
import org.corywixom.cache.data.repository.ICacheItemRepositoryCredentialProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
@ComponentScan(
    {
        "org.corywixom.cache.data",
        "org.corywixom.cache.data.test"
    }
)
public class TestConfiguration {

    @Autowired
    private ICacheItemRepositoryCredentialProvider _credentialProvider;

    @Autowired
    private ICacheItemRepositoryMapper _mapper;

    @Bean
    public ICacheItemRepository cacheItemRepository(){
        return new CacheItemRepository(
            cacheItemRepositoryConfig(),
            _credentialProvider,
            _mapper
        );
    }

    @Bean
    public ICacheItemRepositoryConfig cacheItemRepositoryConfig(){
        return new ICacheItemRepositoryConfig(){

            @Override
            public String region() {
                return "region";
            }

            @Override
            public String tableName() {
                return "tableName";
            }

            @Override
            public String keyName() {
                return "keyName";
            }

            @Override
            public String accessKeyId() {
                return "accessKeyId";
            }

            @Override
            public String secretAccessKey() {
                return "secretAccessKey";
            }
        };
    }
}
