package ru.mts.hw11.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.hw11.entity.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AnimalFactory {

    @Autowired
    final AnimalNames animalNames;

    public AnimalFactory(AnimalNames animalNames) {
        this.animalNames = animalNames;
    }


    /**
     * Метод паттерна Фабрика, создает животное по его типу
     *
     * @param type Enum с типом животного
     * @return Объект созданного животного
     */
    public AbstractAnimal createAnimal(AnimalType type) {
        AbstractAnimal animal = null;
        long minDay = LocalDate.of(1992, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate birthDate = LocalDate.ofEpochDay(randomDay);
        switch (type) {
            case CAT:
                animal = new Cat("Siberian", animalNames.getCatName(), birthDate);
                break;
            case DOG:
                animal = new Dog("Labrador", animalNames.getDogName(), birthDate);
                break;
            case WOLF:
                animal = new Wolf("Arctic", animalNames.getWolfName(), birthDate);
                break;
            case SHARK:
                animal = new Shark("Great White", animalNames.getSharkName(), birthDate);
                break;
        }
        System.out.println("Created animal: " + animal.getName() + " - " + animal.getBreed() + " Birthday: " + animal.getBirthDate());
        return animal;
    }
}
