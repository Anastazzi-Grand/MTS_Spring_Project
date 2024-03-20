package ru.mts.hw11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw11.factory.AnimalFactory;
import ru.mts.hw11.processor.CreateAnimalBeanPostProcessor;
import ru.mts.hw11.service.CreateAnimalService;
import ru.mts.hw11.service.CreateAnimalServiceImpl;


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
