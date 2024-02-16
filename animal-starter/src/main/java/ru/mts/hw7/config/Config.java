package ru.mts.hw7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw7.factory.AnimalFactory;
import ru.mts.hw7.processor.CreateAnimalBeanPostProcessor;
import ru.mts.hw7.service.CreateAnimalService;
import ru.mts.hw7.service.CreateAnimalServiceImpl;


@ComponentScan("ru.mts.hw7")
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
