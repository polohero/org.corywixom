package org.corywixom.cache.restws;

import org.corywixom.cache.data.mapping.ICacheItemRepositoryMapper;
import org.corywixom.cache.data.repository.CacheItemRepository;
import org.corywixom.cache.data.repository.ICacheItemRepository;
import org.corywixom.cache.data.repository.ICacheItemRepositoryConfig;
import org.corywixom.cache.data.repository.ICacheItemRepositoryCredentialProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:productionAppContext.xml"})
@ComponentScan(
        {
            "org.corywixom.cache.business",
            "org.corywixom.cache.restws"
        })
@EnableAutoConfiguration
public class Main extends SpringBootServletInitializer {
        private static final Logger LOG = LoggerFactory.getLogger(Main.class);

        @Autowired
        private ICacheItemRepositoryConfig _cacheItemRepositoryConfig;

        @Autowired
        private ICacheItemRepositoryMapper _cacheItemRepositoryMapper;

        @Autowired
        private ICacheItemRepositoryCredentialProvider _cacheItemRepositoryCredentialProvider;

        @Bean
        public ICacheItemRepository cacheItemRepository(){
                return new CacheItemRepository(
                    _cacheItemRepositoryConfig,
                    _cacheItemRepositoryCredentialProvider,
                    _cacheItemRepositoryMapper
                );
        }


}