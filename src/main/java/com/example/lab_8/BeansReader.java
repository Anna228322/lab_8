package com.example.lab_8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeansReader {
    @Autowired
    private App app;

    public List<FileProcessor> getModulesForFile(String path) {
        ApplicationContext context = app.getContext();

        List<FileProcessor> modules = new ArrayList<>();

        for (String o : context.getBeanDefinitionNames()) {
            Object bean = context.getBean(o);

            FileProcessor processorModule;
            try {
                processorModule = (FileProcessor) bean;
            }
            catch (Exception e) {
                continue;
            }

            if (processorModule.doesSupportFile(path))
                modules.add(processorModule);
        }

        return modules;
    }
}
