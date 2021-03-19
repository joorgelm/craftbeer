package com.beerhouse.domain.usecase;

import java.lang.reflect.Field;

public class BaseInput {

    public boolean checkNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(this) != null)
                return false;
        }
        return true;
    }
}
