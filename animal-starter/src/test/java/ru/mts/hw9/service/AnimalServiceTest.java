package ru.mts.hw9.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.mts.hw9.Application;
import ru.mts.hw9.entity.Cat;
import ru.mts.hw9.config.MockConfig;
import ru.mts.hw9.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {Application.class})
@Import(MockConfig.class)
public class AnimalServiceTest {
    @Autowired
    private CreateAnimalServiceImpl createAnimalServiceImpl;

    @Test
    @DisplayName("Check the results of createAnimal()")
    void initAnimalServiceTest() {
        Animal expectedAnimal = new Cat("Cat", "C", LocalDate.of(2012,12,12));
        Assertions.assertNotNull(createAnimalServiceImpl.getAnimalType());
        Assertions.assertEquals(expectedAnimal, createAnimalServiceImpl.getAnimalType());
    }

    @Test
    @DisplayName("Check the results for positive N in createTenUniqueAnimals")
    void createAnimalsTest() {
        Map<String, List<Animal>> animals = Map.of(
                "Cat", List.of(
                        new Cat("Cat", "C", LocalDate.of(2012,12,12)),
                        new Cat("Cat", "C", LocalDate.of(2012,12,12))));
        int animalCount = 2;

        Assertions.assertEquals(animals, createAnimalServiceImpl.createTenUniqueAnimals(animalCount));
    }

    @Test
    @DisplayName("Проверка выброса исключения в createUniqueAnimals при отрицательном N")
    void createAnimalsThrowTest() {
        int animalCount = -1;

        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalServiceImpl.createTenUniqueAnimals(animalCount));
    }
}
