package org.corywixom.cache.data.repository;

import java.util.HashMap;
import java.util.Map;

import org.corywixom.cache.common.entities.CacheItem;
import org.corywixom.cache.core.utilities.StringUtilities;
import org.corywixom.cache.data.mapping.ICacheItemRepositoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

public class CacheItemRepository implements ICacheItemRepository{
    private static final Logger LOG = LoggerFactory.getLogger(CacheItemRepository.class);
    private static DynamoDbClient _client = null;

    private ICacheItemRepositoryConfig _config;
    private ICacheItemRepositoryCredentialProvider _credentialProvider;
    private ICacheItemRepositoryMapper _mapper;

    public CacheItemRepository(
        ICacheItemRepositoryConfig config,
        ICacheItemRepositoryCredentialProvider credentialProvider,
        ICacheItemRepositoryMapper mapper
    ){
        _config = config;
        _credentialProvider = credentialProvider;
        _mapper = mapper;
    }

    @Override
    public CacheItem get(String key) {
        if(StringUtilities.isNullOrWhitespace(key)){
            throw new NullPointerException(
                "The KEY cannot be NULL when getting an item."
            );
        }

        HashMap<String, AttributeValue> keys =
            new HashMap<>(1);
        keys.put(
            _config.keyName(),
            AttributeValue.builder().s(key).build());

        GetItemRequest request =
            GetItemRequest.builder()
                .key(keys)
                .tableName(_config.tableName())
            .build();

        GetItemResponse response =
            getClient().getItem(
                request);

        if(null != response &&
           response.hasItem()){
            CacheItem cacheItem =_mapper.toEntity(response.item());
            if(false == cacheItem.isExpired()){
                return cacheItem;
            }
        }

        return null;
    }

    @Override
    public CacheItem delete(String key) {

        if(StringUtilities.isNullOrWhitespace(key)){
            throw new NullPointerException(
                "The KEY cannot be NULL when deleting an item."
            );
        }

        HashMap<String, AttributeValue> keys =
            new HashMap<>(1);
        keys.put(
            _config.keyName(),
            AttributeValue.builder().s(key).build());

        CacheItem item = get(key);

        if(null == item){
            return null;
        }

        DeleteItemRequest request =
            DeleteItemRequest.builder()
                .key(keys)
                .tableName(_config.tableName())
                .build();

        getClient().deleteItem(request);

        return item;
    }

    @Override
    public void put(CacheItem cacheItem) {
        isValid(cacheItem);

        Map<String, AttributeValue> attributes =
            _mapper.toDTO(cacheItem);

        PutItemRequest request =
            PutItemRequest.builder()
            .item(attributes)
            .tableName(_config.tableName())
            .build();

        getClient().putItem(request);
    }

    private void isValid(CacheItem cacheItem){
        if(null == cacheItem){
            throw new NullPointerException("The cache item is null.");
        }
        if(StringUtilities.isNullOrWhitespace(cacheItem.getKey())){
            throw new NullPointerException("The cache item key is null.");
        }
        if(StringUtilities.isNullOrWhitespace(cacheItem.getValue())){
            throw new NullPointerException("The cache item value is null.");
        }
        if(null == cacheItem.getExpire()){
            throw new NullPointerException("The cache item expire is null.");
        }
    }

    private DynamoDbClient getClient(){
        if(null == _client){
            _client =
                DynamoDbClient.builder()
                    .region(Region.of(_config.region()))
                    .credentialsProvider(
                        _credentialProvider
                    )
                    .build();
        }

        return _client;
    }





}
