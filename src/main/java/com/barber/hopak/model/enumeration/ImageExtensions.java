package com.barber.hopak.model.enumeration;

import com.barber.hopak.exception.ImageExtensionsNotFoundException;
import com.barber.hopak.util.StringUtils3C;

import java.util.HashMap;
import java.util.Map;

public enum ImageExtensions {

    PNG,
    JPEG,
    JPG;

    private static final Map<String, String> extensions = new HashMap<>();

    static {
        for (ImageExtensions extension : values()) {
            extensions.put(extension.name(), joinDotBeforeExtension(extension.name()));
        }
    }

    public static String getDotExtension(ImageExtensions imageExtensionsName) {
        if (!extensions.containsKey(imageExtensionsName.name())) {
            throw new ImageExtensionsNotFoundException("Your extension is not found");
        }
        return extensions.get(imageExtensionsName.name());
    }

    public static Map<String, String> getExtensions() {
        return Map.copyOf(extensions);
    }

    private static String joinDotBeforeExtension(String extension){
        return StringUtils3C.join('.', extension.toLowerCase());
    }
}
