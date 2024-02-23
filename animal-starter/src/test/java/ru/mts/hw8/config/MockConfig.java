package ru.mts.hw8.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.mts.hw7.entity.*;
import ru.mts.hw7.factory.*;
import ru.mts.hw7.service.CreateAnimalService;
import ru.mts.hw7.service.CreateAnimalServiceImpl;

import java.time.LocalDate;

@TestConfiguration
public class MockConfig {
    @Bean
    @Primary
    public AnimalFactory animalFactoryMock() {
        AnimalFactory animalFactory = Mockito.mock(AnimalFactory.class);
        Mockito.when(animalFactory.createAnimal(AnimalType.CAT)).thenReturn(new Cat("Cat", "C", LocalDate.of(2012,12,12)));
        Mockito.when(animalFactory.createAnimal(AnimalType.DOG)).thenReturn(new Cat("Cat", "C", LocalDate.of(2012,12,12)));
        Mockito.when(animalFactory.createAnimal(AnimalType.WOLF)).thenReturn(new Cat("Cat", "C", LocalDate.of(2012,12,12)));
        Mockito.when(animalFactory.createAnimal(AnimalType.SHARK)).thenReturn(new Cat("Cat", "C", LocalDate.of(2012,12,12)));

        return animalFactory;
    }
}
