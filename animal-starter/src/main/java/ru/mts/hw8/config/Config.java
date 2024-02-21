package ru.mts.hw8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw8.factory.AnimalFactory;
import ru.mts.hw8.processor.CreateAnimalBeanPostProcessor;
import ru.mts.hw8.service.CreateAnimalService;
import ru.mts.hw8.service.CreateAnimalServiceImpl;


@ComponentScan("ru.mts.hw8")
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
