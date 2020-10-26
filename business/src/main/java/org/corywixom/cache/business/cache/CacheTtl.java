package org.corywixom.cache.business.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class CacheTtl implements ICacheTtl{
    @Override
    public long findSeconds(long ttl, TimeUnit timeUnit) {
        return timeUnit.toSeconds(ttl);
    }

    @Override
    public Date findExpiration(long ttlseconds) {
        return new Date(new Date().getTime() + ( ttlseconds * 1000) );
    }

    @Override
    public Date findExpiration(Date time, long ttl, TimeUnit timeUnit) {
        return new Date(time.getTime() + ( timeUnit.toSeconds(ttl) * 1000));
    }
}
