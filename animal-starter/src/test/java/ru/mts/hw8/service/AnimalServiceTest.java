package ru.mts.hw8.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw8.config.MockConfig;
import ru.mts.hw8.entity.Animal;
import ru.mts.hw8.entity.Cat;

import java.time.LocalDate;

@SpringBootTest
@Import(MockConfig.class)
public class AnimalServiceTest {
    @Autowired
    private CreateAnimalService createAnimalService;

    @Test
    @DisplayName("Check the results of createAnimal()")
    void createAnimalTest() {
        Animal expectedAnimal = new Cat("Cat", "C", LocalDate.of(2012,12,12));
        Assertions.assertNotNull(createAnimalService.getAnimalType());
        Assertions.assertEquals(expectedAnimal, createAnimalService.getAnimalType());
    }

    @Test
    @DisplayName("Check the results for positive N in createTenUniqueAnimals")
    void createNAnimalsTest() {
        Animal[] animals = {
                new Cat("Cat", "C", LocalDate.of(2012,12,12)),
                new Cat("Cat", "C", LocalDate.of(2012,12,12))};

        int animalCount = 2;

        Assertions.assertArrayEquals(animals, createAnimalService.createTenUniqueAnimals(animalCount));
    }

    @Test
    @DisplayName("Check the results for negative N in createTenUniqueAnimals")
    void createNAnimalsThrowTest() {
        int animalCount = -1;

        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalService.createTenUniqueAnimals(animalCount));
    }

}
