package ru.mts.hw9.service;

import org.springframework.stereotype.Service;
import ru.mts.hw9.entity.Animal;
import ru.mts.hw9.factory.AnimalFactory;

import java.util.*;

import static ru.mts.hw9.factory.AnimalType.*;


/**
 * интерфейс для создания новых животных и метод для создания 10 уникальных животных
 * */

public interface CreateAnimalService {
    int MAX_COUNT_TYPE_ANIMAL = 4;

    /**
     * Создает 10 уникальных животных с помощью while.
     */
    default Map<String, List<Animal>> createTenUniqueAnimals() {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int i = 0;
        while (i < 10) {
            Animal animal = createAnimal();
            List<Animal> animalList = animalsMap.get(animal.getClass().getSimpleName());
            if (animalList == null){
                animalList = new ArrayList<>();
                animalsMap.put(animal.getClass().getSimpleName(), animalList);
            }
            animalList.add(animal);
            i++;
        }
        return animalsMap;
    }

    /**
     * Создает уникальное животное.
     */
    default Animal createAnimal() {
        Animal animal = null;
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
    AnimalFactory injectForComponent();
    /**
     * Устанавливает тип животного.
     */
    Animal getAnimalType();
    void setAnimalType();
}