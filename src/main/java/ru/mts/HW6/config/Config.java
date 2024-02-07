package ru.mts.HW6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.HW6.processor.CreateAnimalBeanPostProcessor;
import ru.mts.HW6.repository.AnimalsRepository;
import ru.mts.HW6.repository.AnimalsRepositoryImpl;
import ru.mts.HW6.service.CreateAnimalService;
import ru.mts.HW6.service.CreateAnimalServiceImpl;

@Configuration
@ComponentScan("ru.mts.HW6")
public class Config {
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public AnimalsRepository animalsRepository() {
        return new AnimalsRepositoryImpl();
    }

    @Bean
    public CreateAnimalBeanPostProcessor createAnimalServiceBeanPostProcessor(){
        return new CreateAnimalBeanPostProcessor();
    }

}
