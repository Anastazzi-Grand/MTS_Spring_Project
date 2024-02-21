package ru.mts.hw8.config;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.mts.hw8.entity.AbstractAnimal;
import ru.mts.hw8.entity.Cat;
import ru.mts.hw8.service.CreateAnimalService;

import java.time.LocalDate;

@TestConfiguration
public class MockConfig {
    @Bean
    @Primary
    public CreateAnimalService createAnimalServiceMock() {
        CreateAnimalService createAnimalService = Mockito.mock(CreateAnimalService.class);
        AbstractAnimal[] animals = {
                new Cat("Cat", "C", LocalDate.of(2020,12,12)),
                new Cat("Cat", "C", LocalDate.of(2021,12,12)),
                new Cat("Cat", "C", LocalDate.of(2021,12,12))
        };
        Mockito.when(createAnimalService.createTenUniqueAnimals()).thenReturn(animals);
        return createAnimalService;
    }
}
