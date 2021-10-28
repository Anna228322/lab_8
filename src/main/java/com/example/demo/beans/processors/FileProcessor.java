package com.example.demo.beans.processors;

import java.io.PrintStream;

public interface FileProcessor {

    boolean doesSupportFile(String path);

    String getFunctionDescription();

    void execute(String path, PrintStream writer);

}
