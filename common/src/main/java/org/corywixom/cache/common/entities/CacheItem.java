package org.corywixom.cache.common.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CacheItem {
    private String key;
    private String value;
    private Date insert;
    private long ttl;
    private Date expire;

    public boolean isExpired(){
        if(new Date().compareTo(getExpire()) > 0){
            return true;
        }

        return false;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CacheItem)) return false;
        final CacheItem other = (CacheItem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CacheItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final long $ttl = this.getTtl();
        result = result * PRIME + (int) ($ttl >>> 32 ^ $ttl);
        return result;
    }
}
