package com.barber.hopak.buffer;

import com.barber.hopak.util.StringUtils3C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;
import static org.assertj.core.api.Assertions.assertThat;
class BufferContainerStateTest {
    private BufferContainerState bufferContainerState;
    @BeforeEach
    void initState(){
        bufferContainerState = new BufferContainerState();
    }

    @Test
    void add() {
        boolean first = bufferContainerState.add("First" + DOT_TXT);
        boolean second = bufferContainerState.add("Second" + DOT_TXT);
        boolean third = bufferContainerState.add("Third" + DOT_TXT);

        boolean thirdFalse = bufferContainerState.add("Third" + DOT_TXT);
        boolean secondFalse = bufferContainerState.add("Second" + DOT_TXT);
        boolean firstFalse = bufferContainerState.add("First" + DOT_TXT);

        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();

        assertThat(thirdFalse).isFalse();
        assertThat(secondFalse).isFalse();
        assertThat(firstFalse).isFalse();
    }

    @Test
    void isBufferContainName() {
        boolean first = bufferContainerState.add("First" + DOT_TXT);
        boolean second = bufferContainerState.add("Second" + DOT_TXT);
        boolean third = bufferContainerState.add("Third" + DOT_TXT);

        boolean firstContain = bufferContainerState.isBufferContain("First");
        boolean secondContain = bufferContainerState.isBufferContain("Second");
        boolean thirdContain = bufferContainerState.isBufferContain("Third");

        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();

        assertThat(firstContain).isTrue();
        assertThat(secondContain).isTrue();
        assertThat(thirdContain).isTrue();
    }

    @Test
    void isBufferContainId() {
        boolean first = bufferContainerState.add(StringUtils3C.join(1L, ID_SEPARATOR, "First", DOT_TXT));
        boolean second = bufferContainerState.add(StringUtils3C.join(2L, ID_SEPARATOR, "Second", DOT_TXT));
        boolean third = bufferContainerState.add(StringUtils3C.join(3L, ID_SEPARATOR, "Third", DOT_TXT));

        boolean firstContain = bufferContainerState.isBufferContain(1L);
        boolean secondContain = bufferContainerState.isBufferContain(2L);
        boolean thirdContain = bufferContainerState.isBufferContain(3L);
        boolean fourthContain = bufferContainerState.isBufferContain(12L);

        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();


        assertThat(firstContain).isTrue();
        assertThat(secondContain).isTrue();
        assertThat(thirdContain).isTrue();
        assertThat(fourthContain).isFalse();
    }

    @Test
    void removeByName() {
        String firstBufferedName = StringUtils3C.join(1L, ID_SEPARATOR, "First", DOT_TXT);
        String secondBufferedLine = StringUtils3C.join(2L, ID_SEPARATOR, "Second", DOT_TXT);
        String thirdBufferedLine = StringUtils3C.join(3L, ID_SEPARATOR, "Third", DOT_TXT);

        boolean first = bufferContainerState.add(firstBufferedName);
        boolean second = bufferContainerState.add(secondBufferedLine);
        boolean third = bufferContainerState.add(thirdBufferedLine);

        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();

        bufferContainerState.removeByName("First");
        bufferContainerState.removeByName("Second");
        bufferContainerState.removeByName("Third");
        bufferContainerState.removeByName("Fourth");

        first = bufferContainerState.add(firstBufferedName);
        second = bufferContainerState.add(secondBufferedLine);
        third = bufferContainerState.add(thirdBufferedLine);

        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();
    }

    @Test
    void removeById() {

        String firstBufferedName = StringUtils3C.join(1L, ID_SEPARATOR, "First", DOT_TXT);
        String secondBufferedLine = StringUtils3C.join(2L, ID_SEPARATOR, "Second", DOT_TXT);
        String thirdBufferedLine = StringUtils3C.join(3L, ID_SEPARATOR, "Third", DOT_TXT);

        boolean first = bufferContainerState.add(firstBufferedName);
        boolean second = bufferContainerState.add(secondBufferedLine);
        boolean third = bufferContainerState.add(thirdBufferedLine);


        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();

        bufferContainerState.removeById(1L);
        bufferContainerState.removeById(2L);
        bufferContainerState.removeById(3L);
        bufferContainerState.removeById(4L);

        first = bufferContainerState.add(firstBufferedName);
        second = bufferContainerState.add(secondBufferedLine);
        third = bufferContainerState.add(thirdBufferedLine);

        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isTrue();
    }
}