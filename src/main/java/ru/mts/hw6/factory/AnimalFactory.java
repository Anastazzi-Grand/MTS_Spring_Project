package ru.mts.hw6.factory;

import ru.mts.hw6.entity.*;
import ru.mts.hw6.entity.AbstractAnimal;
import ru.mts.hw6.entity.Cat;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactory {
    /**
     * Метод паттерна Фабрика, создает животное по его типу
     *
     * @param type Enum с типом животного
     * @return Объект созданного животного
     */
    public AbstractAnimal createAnimal(AnimalType type) {
        String[] names = {"Fluffy", "Max", "Bella", "Charlie", "Lucy", "Cooper", "Daisy", "Rocky", "Luna", "Buddy"};
        AbstractAnimal animal = null;
        long minDay = LocalDate.of(1992, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate birthDate = LocalDate.ofEpochDay(randomDay);
        String randomName = names[ThreadLocalRandom.current().nextInt(names.length)];
        switch (type) {
            case CAT:
                animal = new Cat("Siberian", randomName, birthDate);
                break;
            case DOG:
                animal = new Dog("Labrador", randomName, birthDate);
                break;
            case WOLF:
                animal = new Wolf("Arctic", randomName, birthDate);
                break;
            case SHARK:
                animal = new Shark("Great White", randomName, birthDate);
                break;
        }
        System.out.println("Создано животное: " + animal.getName() + " - " + animal.getBreed() + " Birthday: " + animal.getBirthDate());
        return animal;
    }
}
