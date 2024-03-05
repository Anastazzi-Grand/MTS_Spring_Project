package ru.mts.hw10.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.mts.hw10.entity.Animal;
import ru.mts.hw10.entity.Cat;
import ru.mts.hw10.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    @Profile("test")
    public CreateAnimalService createAnimalServiceMock() {
        CreateAnimalService createAnimalService = Mockito.mock(CreateAnimalService.class);
        Map<String, List<Animal>> animals = Map.of(
                "Cat", List.of(
                        new Cat("Cat", "C", LocalDate.of(2021,12,12)),
                        new Cat("Cat", "C", LocalDate.of(2021,12,12)),
                        new Cat("Cat", "D", LocalDate.of(2020,12,12))));
        Mockito.when(createAnimalService.createTenUniqueAnimals()).thenReturn(animals);
        return createAnimalService;
    }
}
