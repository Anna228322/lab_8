package com.example.lab_8;

import com.example.lab_8.png.PngResolutionModule;
import com.example.lab_8.txt.TxtRowCountModule;
import com.example.lab_8.txt.TxtSizeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.lab_8")
public class AppConfig {

    public App provideApp() {
        return new App();
    }

    @Bean(name = "scanner")
    public BeansReader provideModuleScanner() {
        return new BeansReader();
    }

    public PngResolutionModule providePngResolutionModule() {
        return new PngResolutionModule();
    }

    public TxtRowCountModule provideTxtRowCountModule() {
        return new TxtRowCountModule();
    }

    public TxtSizeModule provideTxtSizeModule() {
        return new TxtSizeModule();
    }

}
