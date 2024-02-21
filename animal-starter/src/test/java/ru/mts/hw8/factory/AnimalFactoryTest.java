package ru.mts.hw8.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnimalFactoryTest {
    @Autowired
    private AnimalFactory animalFactory;

    @Test
    @DisplayName("Check animalFactory")
    void checkNullAnimalNameTest() {
        Assertions.assertThrows(NullPointerException.class, () -> animalFactory.createAnimal(AnimalType.NO_NAME));
    }

    @Test
    @DisplayName("Check createAnimal")
    void checkNotNullCreateAnimalTest() {
        Assertions.assertNotNull(animalFactory.createAnimal(AnimalType.CAT));
    }
}
