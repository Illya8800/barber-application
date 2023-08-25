package com.barber.hopak.buffer;

import com.barber.hopak.util.StringUtils3C;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;

@Component
@Log4j2
public class BufferContainerState {
    private final Set<String> imageSet = new HashSet<>();
    public boolean add(String bufferedFileName) {
        return imageSet.add(bufferedFileName);
    }
    public boolean isBufferContain(String imageName) {
        return imageSet.stream().anyMatch(setElement -> setElement.endsWith(imageName + DOT_TXT));
    }
    public boolean isBufferContain(Long imageId) {
        return imageSet.stream().anyMatch(imageName -> imageName.startsWith(imageId + ID_SEPARATOR));
    }
    public void removeByName(String name){
        log.info("Deleting file with name {}", name);
        imageSet.stream().filter(imageName -> imageName.endsWith(StringUtils3C.join(ID_SEPARATOR, name, DOT_TXT))).findFirst().ifPresent(imageSet::remove);
    }
    public void removeById(Long id){
        imageSet.stream().filter(imageName -> imageName.startsWith(id.toString())).findFirst().ifPresent(imageSet::remove);
        log.info("Deleting file with id {}", id);
    }
}
