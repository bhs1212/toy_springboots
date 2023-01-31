package com.hasun.toy_springboots.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
    public String getUniqueSequence() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // "src/main/resources/static/files" -> root directory + 상대 경로
    public String getRelativeToAbsolutePath(String relativePath) {
        String relativePathWithSeparator = relativePath.replace("/", File.separator);
        String absolutePath = new File(relativePathWithSeparator).getAbsolutePath() + File.separator;
        return absolutePath;
    }
}
