package com.barber.hopak.util;

public class JsonPart {
    public static String buildJsonString(String field, Long value) {
        return StringUtils3C.join("\"", field, "\":", value.toString(), ",");
    }

    public static String buildJsonString(String field, String value) {
        return StringUtils3C.join("\"", field, "\":\"", value, "\",");
    }
}
