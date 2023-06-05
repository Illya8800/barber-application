package com.barber.hopak.model.enumeration;

import com.barber.hopak.exception.image.inh.ImageExtensionsNotFoundException;
import com.barber.hopak.util.StringUtils3C;

import java.util.Comparator;
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

    public static Integer largestExtensionLengthWithDot() {
        return getExtensions().values().stream()
                .max(Comparator.comparingInt(String::length))
                .map(String::length)
                .orElseThrow(() -> new RuntimeException("Can't find any image extensions in enumeration. You should fill it in ImageExtension.java"));
    }

    private static String joinDotBeforeExtension(String extension) {
        return StringUtils3C.join('.', extension.toLowerCase());
    }
}
