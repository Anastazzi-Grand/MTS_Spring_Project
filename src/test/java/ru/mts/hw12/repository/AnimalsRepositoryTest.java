package ru.mts.hw12.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw12.entity.Animal;
import ru.mts.hw12.entity.Cat;
import ru.mts.hw12.exception.EmptyListOfAnimalsException;
import ru.mts.hw12.exception.FindMinConstAnimalsException;
import ru.mts.hw12.exception.FindOlderAnimalException;
import ru.mts.hw12.Application;
import ru.mts.hw12.Main;
import ru.mts.hw12.config.TestConfig;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Map.entry;

@SpringBootTest(classes = {Main.class, Application.class})
@Import(TestConfig.class)
@ActiveProfiles("test")
public class AnimalsRepositoryTest {
    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    private CopyOnWriteArrayList<Animal> animalsList = new CopyOnWriteArrayList<>();
    @Test
    @DisplayName("Check findDuplicate with empty Map")
    void findDuplicateNotExpectedTest() {
        ConcurrentHashMap<String, List<Animal>> expectedDuplicates = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, List<Animal>> actualDuplicates = animalsRepository.findDuplicate();
        Assertions.assertNotEquals(expectedDuplicates, actualDuplicates);
    }

    @Test
    @DisplayName("Check findLeapYearNames")
    void findLeapYearNamesTest() {
        ConcurrentHashMap<String, LocalDate> expectedAnimals = new ConcurrentHashMap<>();
        expectedAnimals.put("Cat D", LocalDate.of(2020, 12, 12));
        Assertions.assertEquals(expectedAnimals, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Check negative age in findOlderAnimal")
    void findOlderAnimalThrowTest() {
        int age = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimal(age));
    }

    @Test
    @DisplayName("Check animals older than 100 years animals test (no such animals)")
    void findOlderAnimalsNoSuchAnimals() {
        ConcurrentHashMap<Animal, Integer> expected = new ConcurrentHashMap<>();
        expected.put(new Cat("Cat", "D", LocalDate.of(2020, 12, 12)), Period.between(LocalDate.of(2020, 12, 12), LocalDate.now()).getYears());
        Assertions.assertEquals(expected, animalsRepository.findOlderAnimal(100));
    }

    @Test
    @DisplayName("Check findOldAnimalExpensive")
    void findOldAnimalExpensiveTest() {
        CopyOnWriteArrayList<Animal> animalsList = new CopyOnWriteArrayList<>(List.of(
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2010, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(6), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(4), "Cute", LocalDate.of(2015, 1, 1))
        ));
        CopyOnWriteArrayList<Animal> expectedAnimals = new CopyOnWriteArrayList<>(List.of(
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2010, 1, 1))
        ));
        Assertions.assertEquals(expectedAnimals, animalsRepository.findOldAnimalExpensive(animalsList));
    }
    @Test
    @DisplayName("Check findMinConstAnimals")
    void findMinConstAnimals() throws FindMinConstAnimalsException {
        CopyOnWriteArrayList<Animal> animalsList = new CopyOnWriteArrayList<>(List.of(
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "B", new BigDecimal(6), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(15), "Cute", LocalDate.of(2015, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(1), "Cute", LocalDate.of(2010, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2020, 1, 1)),
                new Cat("Cat", "D", new BigDecimal(6), "Cute", LocalDate.of(2015, 1, 1))
        ));
        List<Animal> expectedAnimals = List.of(
                new Cat("Cat", "D", new BigDecimal(6), "Cute", LocalDate.of(2015, 1, 1)),
                new Cat("Cat", "C", new BigDecimal(1), "Cute", LocalDate.of(2010, 1, 1)),
                new Cat("Cat", "B", new BigDecimal(6), "Cute", LocalDate.of(2020, 1, 1))
        );
        Assertions.assertEquals(expectedAnimals, animalsRepository.findMinConstAnimals(animalsList));
    }

    @Test
    @DisplayName("Check FindOlderAnimalException")
    void findOlderAnimalThrowExceptionTest() {
        int age = -1;
        Assertions.assertThrows(FindOlderAnimalException.class, () -> animalsRepository.findOlderAnimal(age));
    }

    @Test
    @DisplayName("Check FindMinConstAnimalsException")
    void findMinConstAnimalsExceptionTest() {
        List<Animal> listOfAnimals = List.of(
                new Cat("Cat", "C", new BigDecimal(10), "Cute", LocalDate.of(2010, 1, 1))
        );
        Assertions.assertThrows(FindMinConstAnimalsException.class, () -> animalsRepository.findMinConstAnimals(new CopyOnWriteArrayList<>(animalsList)));
        Assertions.assertThrows(FindMinConstAnimalsException.class, () -> animalsRepository.findMinConstAnimals(new CopyOnWriteArrayList<>(listOfAnimals)));
    }

    @Test
    @DisplayName("Check EmptyListOfAnimalsException for findAverageAge")
    void findAverageAgeExceptionTest(){
        Assertions.assertThrows(EmptyListOfAnimalsException.class, () -> animalsRepository.findAverageAge(new CopyOnWriteArrayList<>(animalsList)));
    }

    @Test
    @DisplayName("Check EmptyListOfAnimalsException for findOldAnimalExpensive")
    void findOldAnimalExpensiveExceptionTest(){
        Assertions.assertThrows(EmptyListOfAnimalsException.class, () -> animalsRepository.findOldAnimalExpensive(new CopyOnWriteArrayList<>(animalsList)));
    }
}
