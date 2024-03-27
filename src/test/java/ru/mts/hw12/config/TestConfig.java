package ru.mts.hw12.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.mts.hw12.entity.Animal;
import ru.mts.hw12.entity.Cat;
import ru.mts.hw12.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    @Profile("test")
    public CreateAnimalService createAnimalServiceMock() {
        CreateAnimalService createAnimalService = Mockito.mock(CreateAnimalService.class);
        ConcurrentHashMap<String, List<Animal>> animals = new ConcurrentHashMap<>();
        animals.put("Cat", new CopyOnWriteArrayList<>(List.of(
                new Cat("Cat", "C", LocalDate.of(2021,12,12)),
                new Cat("Cat", "C", LocalDate.of(2021,12,12)),
                new Cat("Cat", "D", LocalDate.of(2020,12,12))
        )));
        Mockito.when(createAnimalService.createTenUniqueAnimals()).thenReturn(animals);
        return createAnimalService;
    }
}
