package util;

import java.io.File;

public class folderService {

    public static void createFolder(String targetFolder) {
        File makeChFolder = new File(targetFolder);
        if (!makeChFolder.exists()) {
            makeChFolder.mkdir();
        } else {
            System.out.println("이미 있는 디렉토리 이름 : " + makeChFolder.getPath());
        }
    }
}
