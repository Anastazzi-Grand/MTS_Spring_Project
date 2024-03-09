package ru.mts.hw10.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw10.config.TestConfig;
import ru.mts.hw10.Application;
import ru.mts.hw10.Main;
import ru.mts.hw10.entity.Animal;
import ru.mts.hw10.entity.Cat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

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
        Map<String, List<Animal>> expectedDuplicates = Map.ofEntries(
                entry("Cat", List.of(new Cat("Cat", "C", LocalDate.of(2021,12,12))))
        );
        Map<String, List<Animal>> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Check findDuplicate with empty Map")
    void findDuplicateNotExpectedTest() {
        Map<String, List<Animal>> expectedDuplicates = Map.ofEntries();
        Map<String, List<Animal>> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertNotEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Check findLeapYearNames")
    void findLeapYearNamesTest() {
        Map<String, LocalDate> expectedAnimals = Map.ofEntries(
                entry("Cat D", LocalDate.of(2020,12,12)));
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

    @Test
    @DisplayName("Check findOldAnimalExpensive")
    void findOldAnimalExpensiveTest() {
        List<Animal> animalsList = List.of(
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2010, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(6), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(4), "Cute", LocalDate.of(2015, 1, 1))
        );
        List<Animal> expectedAnimals = List.of(
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2010, 1, 1))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findOldAnimalExpensive(animalsList));
    }
    @Test
    @DisplayName("Check findMinConstAnimals")
    void findMinConstAnimals() {
        List<Animal> animalsList = List.of(
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "B", new BigDecimal(6), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(15), "Cute", LocalDate.of(2015, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(1), "Cute", LocalDate.of(2010, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "D", new BigDecimal(6), "Cute", LocalDate.of(2015, 1, 1))
        );
        List<Animal> expectedAnimals = List.of(
                new Cat("Cat", "D", new BigDecimal(6), "Cute", LocalDate.of(2015, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(1), "Cute", LocalDate.of(2010, 1, 1)),
                new Cat("Cat", "B", new BigDecimal(6), "Cute", LocalDate.of(2020, 1, 1))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findMinConstAnimals(animalsList));
    }
}
