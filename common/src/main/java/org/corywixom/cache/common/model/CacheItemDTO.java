package org.corywixom.cache.common.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.corywixom.cache.common.entities.CacheItem;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CacheItemDTO {
    private String key;
    private String value;
    private Date insert;
    private long ttl;
    private Date expire;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CacheItemDTO)) return false;
        final CacheItemDTO other = (CacheItemDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        if (this.getTtl() != other.getTtl()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CacheItemDTO;
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
