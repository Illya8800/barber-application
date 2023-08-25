package com.barber.hopak.buffer;

import com.barber.hopak.util.StringUtils3C;
import org.junit.jupiter.api.Test;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;
import static org.assertj.core.api.Assertions.assertThat;

class BufferedFileNameTest {

    @Test
    void getBufferedFileName() {
        Long imageId = 1L;
        String imageName = "any name";
        BufferedFileName bufferedFileName = new BufferedFileName(imageId, imageName);
        String fileName = bufferedFileName.getBufferedFileName();
        assertThat(fileName)
                .isEqualTo(StringUtils3C.join(imageId, ID_SEPARATOR, imageName, DOT_TXT));
    }

    @Test
    void getIdFromBufferedFileName() {
        Long imageId = 1L;
        String imageName = "any name";
        String bufferedFileNameMock = StringUtils3C.join(imageId, ID_SEPARATOR, imageName, DOT_TXT);
        BufferedFileName bufferedFileName = new BufferedFileName(imageId, imageName);
        Long expectedId = Long.valueOf(bufferedFileNameMock.substring(0, bufferedFileNameMock.indexOf(ID_SEPARATOR)));
        assertThat(expectedId)
                .isEqualTo(bufferedFileName.getIdFromBufferedFileName(bufferedFileName.getBufferedFileName()));
    }

    @Test
    void getNameFromBufferedFileName() {
        Long imageId = 1L;
        String imageName = "any name";
        String bufferedFileNameMock = StringUtils3C.join(imageId, ID_SEPARATOR, imageName, DOT_TXT);
        BufferedFileName bufferedFileName = new BufferedFileName(imageId, imageName);
        String nameFromBufferedFileName = bufferedFileNameMock.substring(bufferedFileNameMock.indexOf(ID_SEPARATOR) + 1, bufferedFileNameMock.lastIndexOf(DOT_TXT));
        assertThat(nameFromBufferedFileName)
                .isEqualTo(bufferedFileName.getNameFromBufferedFileName(bufferedFileName.getBufferedFileName()));
    }
}