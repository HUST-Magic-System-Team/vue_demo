package com.manli.manli_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(@org.jetbrains.annotations.NotNull SpringApplicationBuilder builder) {
        return builder.sources(Main.class);
    }
}






