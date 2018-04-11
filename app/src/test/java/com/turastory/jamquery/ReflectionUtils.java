package com.turastory.jamquery;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * private 생성자를 Reflection으로 초기화함.
 */
public class ReflectionUtils {
    
    public static void assertFailInstantiation(Class<?> targetClass) {
        final Class<?> cls = targetClass;
        final Constructor<?> c = cls.getDeclaredConstructors()[0];
        c.setAccessible(true);
        
        Throwable targetException = null;
        try {
            c.newInstance((Object[]) null);
        } catch (InvocationTargetException e) {
            targetException = e.getTargetException();
        } catch (IllegalAccessException ignored) {
        
        } catch (InstantiationException ignored) {
        
        }
        
        assertNotNull(targetException);
        assertEquals(targetException.getClass(), InstantiationException.class);
    }
}
