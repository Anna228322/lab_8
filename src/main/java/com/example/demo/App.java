package com.example.demo;

import com.example.demo.beans.AppConfig;
import com.example.demo.beans.processors.FileProcessor;
import com.example.demo.beans.processors.png.PngResolutionModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import com.example.demo.utils.ModuleScanner;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Service
public class App {
	private static final App app = new App();
	private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("No args passed");
			return;
		}

		String path = args[0];

        ApplicationContext context = app.getContext();

		context.getBean("scanner", ModuleScanner.class).getModulesForFile(path);
        PrintStream out = System.out;
        List<FileProcessor> fileProcessors = app.getContext().getBean("scanner", ModuleScanner.class).getModulesForFile(path);


		performInterfaceWork(out, fileProcessors, path);
	}

	private static void performInterfaceWork(PrintStream out, List<FileProcessor> fileProcessors, String path) {
		out.println("Found " + fileProcessors.size() + " modules for file \"" + path + "\"");
		for (FileProcessor fp : fileProcessors) {
			out.println(fp.getClass().getSimpleName());
		}
		if (fileProcessors.size() == 0)
			return;

		int index = -1;
		while (index < 0) {
			out.print("Select processor (input number from 1 to " + fileProcessors.size() + "): ");
			try {
				int option = new Scanner(System.in).nextInt();
				if (option > 0 && option <= fileProcessors.size()) {
					index = option - 1;
				}
				else out.println("Try again");
			}
			catch (Exception ignored) {out.println("Try again");}
		}

		perform(path, fileProcessors.get(index), out);
	}

	public ApplicationContext getContext() {
		return context;
	}

	private static void perform(String path, FileProcessor processorModule, PrintStream out) {
		out.println(processorModule.getFunctionDescription());

		processorModule.execute(path, out);
	}
}
