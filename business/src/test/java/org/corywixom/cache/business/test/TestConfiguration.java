package org.corywixom.cache.business.test;

import org.corywixom.cache.data.repository.ICacheItemRepository;
import org.corywixom.cache.data.repository.ICacheItemRepositoryConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
@ComponentScan(
    {
        "org.corywixom.cache.business",
        "org.corywixom.cache.data"
    }
)
public class TestConfiguration {

    @Bean
    public ICacheItemRepository cacheItemRepository(){
        return new TestCacheItemRepository();
    }

    @Bean
    public ICacheItemRepositoryConfig cacheItemRepositoryConfig(){
        return new ICacheItemRepositoryConfig(){

            @Override
            public String region() {
                return null;
            }

            @Override
            public String tableName() {
                return null;
            }

            @Override
            public String keyName() {
                return null;
            }

            @Override
            public String accessKeyId() {
                return null;
            }

            @Override
            public String secretAccessKey() {
                return null;
            }
        };
    }

}
