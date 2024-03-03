package ru.mts.hw9.service;

import org.springframework.stereotype.Service;
import ru.mts.hw9.entity.Animal;
import ru.mts.hw9.factory.AnimalFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreateAnimalServiceImpl implements CreateAnimalService {

    AnimalFactory animalFactory;
    public CreateAnimalServiceImpl(AnimalFactory animalFactory){
        this.animalFactory = animalFactory;
    }
    private Animal animal;
    /**
     * Создает 10 уникальных животных с помощью do-while.
     */
    public Map<String, List<Animal>> createTenUniqueAnimals(){
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int i = 0;
        do {
            Animal animal = createAnimal();
            List<Animal> animalList = animalsMap.get(animal.getClass().getSimpleName());
            if (animalList == null){
                animalList = new ArrayList<>();
                animalsMap.put(animal.getClass().getSimpleName(), animalList);
            }
            animalList.add(animal);
            i++;
        } while (i < 10);
        return animalsMap;
    }

    /**
     * Метод для создания N животных
     * @param n количество животных, которые необходимо создать
     */
    public Map<String, List<Animal>> createTenUniqueAnimals(int n){
        if (n < 0) throw new IllegalArgumentException("N should be > 0");
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        for (int i = 0; i < n; i++){
            Animal animal = createAnimal();
            List<Animal> animalList = animalsMap.get(animal.getClass().getSimpleName());
            if (animalList == null){
                animalList = new ArrayList<>();
                animalsMap.put(animal.getClass().getSimpleName(), animalList);
            }
            animalList.add(animal);
        }
        return animalsMap;
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
