package com.Ecommercee.utiles.allurereport;

import com.Ecommercee.utiles.FilesUtils;
import com.Ecommercee.utiles.OSUtils;
import com.Ecommercee.utiles.TerminalUtils;
import com.Ecommercee.utiles.TimeManager;
import com.Ecommercee.utiles.logs.logsManager;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.Ecommercee.utiles.allurereport.AllureConstants.HISTORY_FOLDER;
import static com.Ecommercee.utiles.allurereport.AllureConstants.RESULTS_HISTORY_FOLDER;
import static com.Ecommercee.utiles.dataReader.PropertyReader.getProperty;

public class AllureReportGenerator {
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
    }

    public static String renameReport() {
        String newFileName = AllureConstants.REPORT_PREFIX + TimeManager.getTimeStamp() + AllureConstants.REPORT_EXTENSION;
        FilesUtils.renameFile(AllureConstants.REPORT_PATH.resolve(AllureConstants.INDEX_HTML).toString(), newFileName);
        return newFileName;
    }

    public static void openReport(String reportFileName) {
        if (!getProperty("OpenAllureReportAfterExecution").equalsIgnoreCase("true")) return;

        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSUtils.getCurrentOS()) {
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe", "/c", "start", reportPath.toString());
            case MAC, LINUX -> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> logsManager.warn("Opening Allure Report is not supported on this OS.");
        }
    }

    public static void copyHistory() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            logsManager.error("Error copying history files", e.getMessage());
        }
    }
}
