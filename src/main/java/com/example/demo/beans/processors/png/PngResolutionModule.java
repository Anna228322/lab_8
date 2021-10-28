package com.example.demo.beans.processors.png;

import com.example.demo.beans.processors.FileProcessor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

@Component
public class PngResolutionModule implements FileProcessor {
    @Override
    public boolean doesSupportFile(String path) {
        return path != null && path.contains(".") && path.split("\\.")[path.split("\\.").length - 1].equals("png");
    }

    @Override
    public String getFunctionDescription() {
        return "Prints width and height of image";
    }

    @Override
    public void execute(String path, PrintStream writer) {
        try {
            if (!doesSupportFile(path))
                throw new IOException("Invalid type");
            BufferedImage image = ImageIO.read(new File(path));
            writer.println(path + " has width=" + image.getWidth() + " and height=" + image.getHeight());
        }
        catch (Exception e) {
            writer.println("[ERROR] Failed to process file:\n\t\t\t" + e.getLocalizedMessage() + " lines");
            e.printStackTrace(writer);
        }

    }
}
