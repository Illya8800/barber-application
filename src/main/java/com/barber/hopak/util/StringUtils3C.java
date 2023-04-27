package com.barber.hopak.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils3C {
    public static String join(Object... s){
        return org.apache.commons.lang3.StringUtils.join(
                Arrays.stream(s).map(Object::toString).collect(Collectors.toList()), null
        );
    }
}
