package org.corywixom.cache.common.test;

import org.corywixom.cache.core.utilities.ObjectUtilities;

public class GetterAndSetterException extends Exception {

    public GetterAndSetterException(String message){
        super(message);
    }

    public GetterAndSetterException(Object fieldOne, Object fieldTwo, String message) {
        super(ObjectUtilities.toPrintString(fieldOne) + ".equals(" +
                  ObjectUtilities.toPrintString(fieldTwo  ) + ") returned false. " +
                  ObjectUtilities.toPrintString(message));
    }

}