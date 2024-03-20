package ru.mts.hw11.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw11.Application;
import ru.mts.hw11.Main;
import ru.mts.hw11.entity.Animal;
import ru.mts.hw11.entity.Cat;
import ru.mts.hw11.exception.EmptyListOfAnimalsException;
import ru.mts.hw11.exception.FindMinConstAnimalsException;
import ru.mts.hw11.exception.FindOlderAnimalException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = {Main.class, Application.class})
@ActiveProfiles("test")
public class AnimalsRepositoryTest {
    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    private List<Animal> animalsList = null;

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
        Assertions.assertThrows(FindMinConstAnimalsException.class, () -> animalsRepository.findMinConstAnimals(animalsList));
        Assertions.assertThrows(FindMinConstAnimalsException.class, () -> animalsRepository.findMinConstAnimals(listOfAnimals));
    }

    @Test
    @DisplayName("Check EmptyListOfAnimalsException for findAverageAge")
    void findAverageAgeExceptionTest(){
        Assertions.assertThrows(EmptyListOfAnimalsException.class, () -> animalsRepository.findAverageAge(animalsList));
    }

    @Test
    @DisplayName("Check EmptyListOfAnimalsException for findOldAnimalExpensive")
    void findOldAnimalExpensiveExceptionTest(){
        Assertions.assertThrows(EmptyListOfAnimalsException.class, () -> animalsRepository.findOldAnimalExpensive(animalsList));
    }
}
