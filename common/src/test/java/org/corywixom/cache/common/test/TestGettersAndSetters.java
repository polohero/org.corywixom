package org.corywixom.cache.common.test;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class TestGettersAndSetters {

    /**
     * This test may appear to do nothing, but
     * it's purpose is to ensure that Lombok
     * doesn't inject something that fails.
     * It creates an instance of each class
     * and calls equals, toString, and get/set.
     * @throws GetterAndSetterException
     */
    @Test
    public void testAllGettersAndSetters() throws GetterAndSetterException{

        final String PACKAGE_NAME = "org.corywixom.cache.common";

        Reflections reflections = new Reflections(PACKAGE_NAME,
                                                  Object.class.getClassLoader(),
                                                  new SubTypesScanner(false));

        Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);

        // Loop through every object.
        for (Class<? extends Object> pojoClass :classes){
            try {

                Object object = pojoClass.newInstance();


                // Get every field within the Object.
                for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {

                    try {

                        // Ignore the Class field.
                        if(pd.getPropertyType().getSimpleName().equals("Class")){
                            continue;
                        }

                        // Generate a new instance of the field.
                        Object fieldOne = newInstance(pd);
                        Object fieldTwo = null;

                        if (pd.getWriteMethod() != null &&
                            pd.getReadMethod() != null)
                        {
                            // Call the setter
                            pd.getWriteMethod().invoke(object, fieldOne);

                            // Call the getter.
                            fieldTwo = pd.getReadMethod().invoke(object);

                            // Validate result from the getter matches what
                            // we passed to the setter.
                            if(false == Objects.equals(fieldOne, fieldTwo)){
                                throw new GetterAndSetterException(
                                    fieldOne,
                                    fieldTwo,
                                    "Class: " + pd.getPropertyType().getName() +
                                        "Field: " + pd.getName()
                                );
                            }
                        }


                    }
                    catch (GetterAndSetterException getterAndSetterException){
                        throw  getterAndSetterException;
                    }
                }

                Object other = pojoClass.newInstance();
                other.equals(object);
                object.equals(other);
                object.hashCode();
                object.toString();

            }
            catch (GetterAndSetterException getterAndSetterExceptionParent) {
                throw getterAndSetterExceptionParent;
            }
            catch (Exception e) {
                System.out.println(
                    "Class: " + pojoClass.getName() + ": cannot have an instanced created. " +
                        "This may be OK. The getters and setters just won't get tested. " +
                        e.getMessage());
            }
        }
    }

    private Object newInstance(Class theClass) throws Exception{
        if (theClass.getSimpleName().equals("String")) {
            return RandomStringUtils.randomAlphanumeric(5);
        } else if (theClass.getSimpleName().equals("BigDecimal")) {
            return new BigDecimal(RandomStringUtils.randomNumeric(4));
        } else if (theClass.getSimpleName().equals("Integer")) {
            return new Integer(RandomStringUtils.randomNumeric(2));
        } else if (theClass.getSimpleName().equals("int")) {
            return new Integer(RandomStringUtils.randomNumeric(2));
        }
        else if (theClass.getSimpleName().equals("long")) {
            return new Long(RandomStringUtils.randomNumeric(2));
        }
        else if (theClass.getSimpleName().equals("Long")) {
            return new Long(RandomStringUtils.randomNumeric(2));
        }
        else if (theClass.getSimpleName().equals("Boolean")) {
            return new Boolean(true);
        }
        else if (theClass.getSimpleName().equals("boolean")) {
            return new Boolean(true);
        }
        else if (theClass.getSimpleName().equals("List")) {
            return new ArrayList<>();
        } else if (theClass.getSimpleName().equals("Set")) {
            return new HashSet<>();
        } else if (theClass.getSimpleName().equals("UUID")) {
            return UUID.randomUUID();
        }

        return theClass.newInstance();
    }

    private Object newInstance(PropertyDescriptor pd) throws Exception {
        return newInstance(pd.getPropertyType());
    }
}
