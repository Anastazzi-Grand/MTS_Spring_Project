package ru.mts.hw8.service;

import ru.mts.hw8.entity.AbstractAnimal;
import ru.mts.hw8.entity.Animal;
import ru.mts.hw8.factory.AnimalFactory;

import java.util.Random;

import static ru.mts.hw8.factory.AnimalType.*;


/**
 * интерфейс для создания новых животных и метод для создания 10 уникальных животных
 * */

public interface CreateAnimalService {
    int MAX_COUNT_TYPE_ANIMAL = 4;

    /**
     * Создает 10 уникальных животных с помощью while.
     */
    default AbstractAnimal[] createTenUniqueAnimals() {
        AbstractAnimal[] animals = new AbstractAnimal[10];
        int i = 0;
        while (i < animals.length) {
            animals[i] = createAnimal();
            i++;
        }
        return animals;
    }

    /**
     * Создает уникальное животное.
     */
    default AbstractAnimal createAnimal() {
        AbstractAnimal animal = null;
        AnimalFactory animalFactory = injectForComponent();
        Random random = new Random();
        int randomNumber = random.nextInt(MAX_COUNT_TYPE_ANIMAL);
        switch (randomNumber) {
            case 0:
                animal = animalFactory.createAnimal(CAT);
                break;
            case 1:
                animal = animalFactory.createAnimal(DOG);
                break;
            case 2:
                animal = animalFactory.createAnimal(WOLF);
                break;
            case 3:
                animal = animalFactory.createAnimal(SHARK);
                break;

        }
        return animal;
    }

    AbstractAnimal[] createTenUniqueAnimals(int n);

    AnimalFactory injectForComponent();
    /**
     * Устанавливает тип животного.
     */
    Animal getAnimalType();
    void setAnimalType();
}