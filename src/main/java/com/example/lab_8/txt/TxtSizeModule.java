package com.example.lab_8.txt;

import com.example.lab_8.FileProcessor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TxtSizeModule implements FileProcessor {
    @Override
    public boolean doesSupportFile(String path) {
        return path != null && path.contains(".") && path.split("\\.")[path.split("\\.").length - 1].equals("txt");
    }

    @Override
    public String getDescription() {
        return "Prints the size of txt file";
    }

    @Override
    public void execute(String path, PrintStream writer) {
        try {
            if (!doesSupportFile(path))
                throw new IOException("Invalid type");
            writer.println("Size of " + path + " is " + Files.size(Path.of(path)) + " bytes");
        }
        catch (Exception e) {
            writer.println("[ERROR] Failed to get txt file size:\n\t\t\t" + e.getLocalizedMessage());
            e.printStackTrace(writer);
        }
    }
}
