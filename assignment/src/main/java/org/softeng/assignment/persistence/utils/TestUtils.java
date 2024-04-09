package org.softeng.assignment.persistence.utils;

import org.softeng.assignment.application.Main;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

public class TestUtils {
    private static final File DB_SRC = new File("assignment/src/main/assets/db/WellSyncDB.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
