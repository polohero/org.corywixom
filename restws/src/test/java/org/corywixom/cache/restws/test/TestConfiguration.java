package org.corywixom.cache.restws.test;

import org.corywixom.cache.business.test.TestCacheItemRepository;
import org.corywixom.cache.data.repository.ICacheItemRepository;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
@ComponentScan(
    {
        "org.corywixom.cache.data",
        "org.corywixom.cache.business",
        "org.corywixom.cache.restws"
    }
)
public class TestConfiguration {

    @Bean
    public ICacheItemRepository cacheItemRepository(){
        return new TestCacheItemRepository();
    }
}
