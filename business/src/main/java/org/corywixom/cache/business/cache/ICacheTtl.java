package org.corywixom.cache.business.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public interface ICacheTtl {

    long findSeconds(long ttl, TimeUnit timeUnit);

    Date findExpiration(long ttlseconds);

    Date findExpiration(Date time, long ttl, TimeUnit timeUnit);
}
