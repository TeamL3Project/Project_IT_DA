package util;

import java.io.IOException;
import java.nio.file.*;

public class fileCopyService {


    public static void fileCopy(String source, String target) {
        Path sourceFilePath = Path.of(source);
        Path targetFilePath = Path.of(target);
        try {
            Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("파일이 복사되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
