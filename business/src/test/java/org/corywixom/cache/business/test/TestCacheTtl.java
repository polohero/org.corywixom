package org.corywixom.cache.business.test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.corywixom.cache.business.cache.ICacheTtl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class TestCacheTtl extends BusinessTestBase{

    @Autowired
    private ICacheTtl _ttl;

    @Test
    public void testFindSeconds(){

        assertIt(
            _ttl.findSeconds(1, TimeUnit.MINUTES) == 60,
            _ttl.findSeconds(1, TimeUnit.MINUTES) + "",
            "60",
            "Should be 60 seconds."
        );
    }

    @Test
    public void testFindExpiration_Seconds(){

        Date before = new Date();
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(before);

        Date expiration = _ttl.findExpiration(86400);
        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.setTime(expiration);

        assertIt(
            beforeCalendar.get(Calendar.DAY_OF_YEAR) == expirationCalendar.get(Calendar.DAY_OF_YEAR) - 1,
            expirationCalendar.get(Calendar.DAY_OF_YEAR) - 1 + "",
            beforeCalendar.get(Calendar.DAY_OF_YEAR) + "",
            "Should be incremented by 1 day."
        );
    }

    @Test
    public void testFindExpiration_Minutes(){

        Date before = new Date();
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(before);

        Date expiration = _ttl.findExpiration(new Date(), 1440, TimeUnit.MINUTES);
        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.setTime(expiration);

        assertIt(
            beforeCalendar.get(Calendar.DAY_OF_YEAR) == expirationCalendar.get(Calendar.DAY_OF_YEAR) - 1,
            expirationCalendar.get(Calendar.DAY_OF_YEAR) - 1 + "",
            beforeCalendar.get(Calendar.DAY_OF_YEAR) + "",
            "Should be incremented by 1 day."
        );
    }
}
