package ru.mts.hw8.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.mts.hw6.entity.Animal;
import ru.mts.hw8.Application;
import ru.mts.hw8.Main;
import ru.mts.hw8.config.MockConfig;
import ru.mts.hw8.service.CreateAnimalService;

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
        Set<String> expectedDuplicates = new HashSet<>();
        expectedDuplicates.add("C");
        Assertions.assertEquals(expectedDuplicates, animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("Check findDuplicate with empty Set")
    void findDuplicateEmptyTest() {
        Set<String> expectedDuplicates = new HashSet<>();
        Assertions.assertNotEquals(expectedDuplicates, animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("Check findLeapYearNames")
    void findLeapYearNamesTest() {
        String[] expectedAnimalsNames = {"C"};
        Assertions.assertArrayEquals(expectedAnimalsNames, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Check negative age in findOlderAnimal")
    void findOlderAnimalThrowTest() {
        int age = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimal(age));
    }

}
