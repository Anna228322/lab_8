package com.example.lab_8;

import java.io.PrintStream;

public interface FileProcessor {

    boolean doesSupportFile(String path);

    String getDescription();

    void execute(String path, PrintStream writer);

}
