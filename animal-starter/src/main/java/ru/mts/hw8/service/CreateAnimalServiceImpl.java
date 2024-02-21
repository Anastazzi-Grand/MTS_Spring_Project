package ru.mts.hw8.service;

import ru.mts.hw8.entity.AbstractAnimal;
import ru.mts.hw8.entity.Animal;
import ru.mts.hw8.factory.AnimalFactory;


public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final AnimalFactory animalFactory;
    public CreateAnimalServiceImpl(AnimalFactory animalFactory){
        this.animalFactory = animalFactory;
    }
    private Animal animal;
    /**
     * Создает 10 уникальных животных с помощью do-while.
     */
    @Override
    public AbstractAnimal[] createTenUniqueAnimals(){
        AbstractAnimal[] animals = new AbstractAnimal[10];
        int i = 0;
        do {
            animals[i] = createAnimal();
            i++;
        } while (i < 10);
        return animals;
    }

    /**
     * Метод для создания N животных
     * @param n количество животных, которые необходимо создать
     */
    @Override
    public AbstractAnimal[] createTenUniqueAnimals(int n){
        if (n < 0) {
            throw new IllegalArgumentException("Cannot create negative count of animals");
        }
        AbstractAnimal[] animals = new AbstractAnimal[n];
        for (int i = 0; i < n; i++){
            animals[i] = createAnimal();
        }
        return animals;
    }

    @Override
    public AnimalFactory injectForComponent(){
        return animalFactory;
    }
    @Override
    public Animal getAnimalType() {
        return animal;
    }

    @Override
    public void setAnimalType() {
        animal = createAnimal();
    }
}
