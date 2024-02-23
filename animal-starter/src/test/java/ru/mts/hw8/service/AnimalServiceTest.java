package ru.mts.hw8.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.mts.hw7.Application;
import ru.mts.hw7.entity.*;
import ru.mts.hw7.service.CreateAnimalService;
import ru.mts.hw7.service.CreateAnimalServiceImpl;
import ru.mts.hw8.config.MockConfig;


import java.time.LocalDate;

@SpringBootTest(classes = {Application.class})
@Import(MockConfig.class)
public class AnimalServiceTest {
    @Autowired
    private CreateAnimalServiceImpl createAnimalServiceImpl;

    @Test
    @DisplayName("Check the results of createAnimal()")
    void createAnimalTest() {
        Animal expectedAnimal = new Cat("Cat", "G", LocalDate.of(2012,12,12));
        Assertions.assertNotNull(createAnimalServiceImpl.getAnimalType());
        Assertions.assertNotEquals(expectedAnimal, createAnimalServiceImpl.getAnimalType());
    }

    @Test
    @DisplayName("Check the results for positive N in createTenUniqueAnimals")
    void createNAnimalsTest() {
        Animal[] animals = {
                new Cat("Cat", "C", LocalDate.of(2012,12,12)),
                new Cat("Cat", "C", LocalDate.of(2012,12,12))};

        int animalCount = 2;

        Assertions.assertArrayEquals(animals, createAnimalServiceImpl.createTenUniqueAnimals(animalCount));
    }

    @Test
    @DisplayName("Check the results for negative N in createTenUniqueAnimals")
    void createNAnimalsThrowTest() {
        int animalCount = -1;

        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalServiceImpl.createTenUniqueAnimals(animalCount));
    }

}
