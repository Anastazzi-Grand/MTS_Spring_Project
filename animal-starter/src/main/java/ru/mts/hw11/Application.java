package ru.mts.hw11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("animal-starter work");
        SpringApplication.run(Application.class, args);
    }
}
