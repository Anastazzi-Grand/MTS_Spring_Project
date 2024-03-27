package ru.mts.hw12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw12.factory.AnimalFactory;
import ru.mts.hw12.processor.CreateAnimalBeanPostProcessor;
import ru.mts.hw12.service.CreateAnimalService;
import ru.mts.hw12.service.CreateAnimalServiceImpl;


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
