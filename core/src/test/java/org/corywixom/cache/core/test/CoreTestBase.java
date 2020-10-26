package org.corywixom.cache.core.test;

import org.junit.Assert;

public class CoreTestBase {

    public void assertIt(boolean result, String current, String expected, String message) {
        Assert.assertTrue(
            "Curent:" + (null == current ? "NULL" : current) +
                "\n" +
                "Expected:" + (null == expected ? "NULL" : expected) +
                "\n" +
                "Message:" + (null == message ? "NULL" : message),
            result
        );
    }
}
