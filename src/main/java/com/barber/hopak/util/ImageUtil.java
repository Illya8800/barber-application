package com.barber.hopak.util;

import com.barber.hopak.model.enumeration.ImageExtensions;

import static com.barber.hopak.model.enumeration.ImageExtensions.PNG;

public class ImageUtil {
    public static String BUFFER_FOLDER_NAME = "images";
    public static final String CONVERTER_SPLIT_REGEX = " ";
    public static final int MAX_IMAGE_SIZE = 16777216;
    public static final String NO_IMAGE = StringUtils3C.join("no_image", ImageExtensions.getDotExtension(PNG));
    public static final String DOT_TXT = ".txt";
    public static final String ID_SEPARATOR = "-";

    public static final String FOLDER_SEPARATOR = "/";
}
