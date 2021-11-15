package com.example.lab_8.txt;

import com.example.lab_8.FileProcessor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TxtRowCountModule implements FileProcessor {
    @Override
    public boolean doesSupportFile(String path) {
        return path != null && path.contains(".") && path.split("\\.")[path.split("\\.").length - 1].equals("txt");
    }

    @Override
    public String getDescription() {
        return "Prints count of rows in file";
    }

    @Override
    public void execute(String path, PrintStream writer) {
        try {
            if (!doesSupportFile(path))
                throw new IOException("Invalid type");
            List<String> lines = new ArrayList<>();
            try (Stream<String> stream = Files.lines(Paths.get(path))) {
                stream.forEach(lines::add);
            }
            writer.println(path + " contains " + lines.size() + " lines");
        }
        catch (Exception e) {
            writer.println("[ERROR] Failed to process file:\n\t\t\t" + e.getLocalizedMessage() + " lines");
            e.printStackTrace(writer);
        }
    }
}
