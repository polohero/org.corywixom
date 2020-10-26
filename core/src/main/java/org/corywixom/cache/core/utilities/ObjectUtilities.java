package org.corywixom.cache.core.utilities;

public class ObjectUtilities {

    private static final String NULL = "NULL";

    public static String toPrintString(Object object) {
        if (null == object) {
            return NULL;
        }

        return object.toString();
    }
}
