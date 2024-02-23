package ru.mts.hw8.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.mts.hw7.entity.Animal;
import ru.mts.hw7.Application;
import ru.mts.hw7.Main;
import ru.mts.hw7.entity.Cat;
import ru.mts.hw7.entity.Dog;
import ru.mts.hw7.repository.AnimalsRepositoryImpl;
import ru.mts.hw8.config.MockConfig;
import ru.mts.hw7.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = {Main.class, Application.class})
@Import(MockConfig.class)
public class AnimalsRepositoryTest {
    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Test
    @DisplayName("Check findDuplicate")
    void findDuplicateTest() {
        Set<Animal> expectedDuplicates = new HashSet<>();
        expectedDuplicates.add(new Cat("Cat", "C", LocalDate.of(2021,12,12)));
        Assertions.assertEquals(expectedDuplicates, animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("Check findDuplicate with wrong Set")
    void findDuplicateEmptyTest() {
        Set<Animal> expectedDuplicates = new HashSet<>();
        expectedDuplicates.add(new Dog("Dog", "C", LocalDate.of(2021,12,12)));
        Assertions.assertNotEquals(expectedDuplicates, animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("Check findLeapYearNames")
    void findLeapYearNamesTest() {
        String[] expectedAnimalsNames = {};
        Assertions.assertArrayEquals(expectedAnimalsNames, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Check negative age in findOlderAnimal")
    void findOlderAnimalThrowTest() {
        int age = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimal(age));
    }

}
