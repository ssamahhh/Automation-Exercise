package com.Ecommercee.utiles;

import com.Ecommercee.utiles.logs.logsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyFile;

public class FilesUtils {
    private final static String USER_DIR = System.getProperty("user.dir") + File.separator;

    public static void renameFile(String filePath, String newFileName) {
        try {
            var targetFile = new File(filePath);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newFileName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                FileUtils.deleteQuietly(targetFile);
                logsManager.info("Target File Path: \"" + filePath + "\", file was renamed to \"" + newFileName + "\".");
            } else {
                logsManager.info(("Target File Path: \"" + filePath + "\", already has the desired name \"" + newFileName + "\"."));
            }
        } catch (IOException e) {
            logsManager.error(e.getMessage());
        }
    }

    //creating dirs if not exist
    public static void createDirs(String path) {
        try {
            //File file = new File(ConfigUtils.getConfigValue("user.dir") + File.separator + path);
            File file = new File( USER_DIR+ path);
            if (!file.exists()) {
                file.mkdirs();
                logsManager.info("Directory created: " + path);
            }
        } catch (Exception e) {
            logsManager.error("Failed to create directory: " + e.getMessage());
        }

    }

    public static void forceDelete(File logs) {
        try {
            FileUtils.forceDeleteOnExit(logs);
        } catch (IOException e) {
            logsManager.error("Failed to delete file: " + logs.getAbsolutePath() + " due to: " + e.getMessage());
        }
    }

    public static void cleanDirectory(File file) {
        try {
            FileUtils.deleteQuietly(file);
        } catch (Exception exception) {
            logsManager.error(exception.getMessage());
        }
    }

}
