package com.barber.hopak.util;

import com.barber.hopak.model.enumeration.ImageExtensions;

import static com.barber.hopak.model.enumeration.ImageExtensions.PNG;

public class ImageUtil {
    public static final Long MAX_IMAGE_SIZE = 16777216L;
    public static final String NO_IMAGE = "NO_IMAGE" + ImageExtensions.getDotExtension(PNG);

}
