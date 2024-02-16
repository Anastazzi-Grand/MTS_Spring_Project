package ru.mts.hw7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    public static void main(String[] args) {
        System.out.println("animal-starter work");
        SpringApplication.run(Application.class, args);
    }
}
