package util;

import java.io.File;

public class folderService {

	public static void createFolder(String targetFolder) {
		File makeChFolder = new File(targetFolder);
		if (!makeChFolder.exists()) {
			makeChFolder.mkdir();
		}
	}
}
