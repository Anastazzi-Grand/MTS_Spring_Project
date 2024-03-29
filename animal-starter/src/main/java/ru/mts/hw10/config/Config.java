package ru.mts.hw10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw10.factory.AnimalFactory;
import ru.mts.hw10.processor.CreateAnimalBeanPostProcessor;
import ru.mts.hw10.service.CreateAnimalService;
import ru.mts.hw10.service.CreateAnimalServiceImpl;


@Configuration
public class Config {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessorImpl() {
        return new CreateAnimalBeanPostProcessor();
    }

    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory);
    }
}
