package com.example.demo.beans;

import com.example.demo.App;
import com.example.demo.beans.processors.png.PngResolutionModule;
import com.example.demo.beans.processors.log.LogRowCountModule;
import com.example.demo.beans.processors.log.LogSizeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.example.demo.utils.ModuleScanner;

@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class AppConfig {

    public App provideApp() {
        return new App();
    }

    @Bean(name = "scanner")
    public ModuleScanner provideModuleScanner() {
        return new ModuleScanner();
    }

    public PngResolutionModule providePngResolutionModule() {
        return new PngResolutionModule();
    }

    public LogRowCountModule provideTxtRowCountModule() {
        return new LogRowCountModule();
    }

    public LogSizeModule provideTxtSizeModule() {
        return new LogSizeModule();
    }

}
