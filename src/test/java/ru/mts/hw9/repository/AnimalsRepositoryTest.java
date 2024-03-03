package ru.mts.hw9.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw9.Application;
import ru.mts.hw9.Main;
import ru.mts.hw9.config.TestConfig;
import ru.mts.hw9.entity.Animal;
import ru.mts.hw9.entity.Cat;
import ru.mts.hw9.entity.Dog;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

@SpringBootTest(classes = {Main.class, Application.class})
@Import(TestConfig.class)
@ActiveProfiles("test")
public class AnimalsRepositoryTest {
    @Autowired
    private AnimalsRepositoryImpl animalsRepository;
    @Test
    @DisplayName("Check findDuplicate")
    void findDuplicateTest() {
        Map<String, Integer> expectedDuplicates = Map.ofEntries(
                entry("Cat", 2)
        );
        Map<String, Integer> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Check findDuplicate with empty Map")
    void findDuplicateEmptyTest() {
        Map<String, Integer> expectedDuplicates = Map.ofEntries();
        Map<String, Integer> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertNotEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Check findLeapYearNames")
    void findLeapYearNamesTest() {
        Map<String, LocalDate> expectedAnimals = Map.ofEntries(
                entry("Cat D", LocalDate.of(2020,12,12))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Check negative age in findOlderAnimal")
    void findOlderAnimalThrowTest() {
        int age = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimal(age));
    }

    @Test
    @DisplayName("Check animals older then 100 years animals test (no such animals)")
    void findOlderAnimalsNoSuchAnimals(){
        Map<Animal, Integer> expected = Map.ofEntries(
                entry( new Cat("Cat", "D", LocalDate.of(2020,12,12)), Period.between(LocalDate.of(2020, 12,12), LocalDate.now()).getYears())
        );
        Assertions.assertEquals(expected, animalsRepository.findOlderAnimal(100));
    }
}
