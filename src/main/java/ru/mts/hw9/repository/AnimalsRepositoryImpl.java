package ru.mts.hw9.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.mts.hw9.entity.Animal;
import ru.mts.hw9.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    protected List<Animal> animals = new ArrayList<>();;

    @Autowired
    CreateAnimalService animalService;

    @PostConstruct
    private void postConstruct(){
        Map<String,List<Animal>> animalsMap = animalService.createTenUniqueAnimals();
        for (Map.Entry<String,List<Animal>> entry : animalsMap.entrySet()) {
            animals.addAll(entry.getValue());
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String,LocalDate> leapYearNames = new HashMap<>();
        for (Animal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                leapYearNames.put(animal.getBreed() + " " + animal.getName(), animal.getBirthDate());
            }
        }
        return leapYearNames;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        if (n < 0) throw new IllegalArgumentException("N should be > 0");
        Map<Animal, Integer> olderAnimalMap = new HashMap<>();
        for (Animal animal : animals) {
            Integer age = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
            if (age > n) olderAnimalMap.put(animal, age);
        }
        if (olderAnimalMap.isEmpty()) {
            Animal oldestAnimal = animals.get(0);
            for (Animal animal : animals) {
                if (oldestAnimal.getBirthDate().getYear() > animal.getBirthDate().getYear()) {
                    oldestAnimal = animal;
                }
            }
            olderAnimalMap.put(oldestAnimal, Period.between(oldestAnimal.getBirthDate(), LocalDate.now()).getYears());
        }
        return olderAnimalMap;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        Map<String, Integer> findDuplicateMap = new HashMap<>();
        Map<Animal, Integer> animalCountMap = new HashMap<>();
        for (Animal animal : animals) {
            animalCountMap.put(animal, animalCountMap.getOrDefault(animal, 0) + 1);
        }
        for (Map.Entry<Animal, Integer> entry : animalCountMap.entrySet()) {
            Animal animal = entry.getKey();
            int count = entry.getValue();
            if (count > 1){
                findDuplicateMap.put(animal.getClass().getSimpleName(), findDuplicateMap.getOrDefault(animal.getClass().getSimpleName(), 0) + count);
            }
        }
        return findDuplicateMap;
    }

    @Override
    public void printDuplicate() {
        Map<String, Integer> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }
        for (Map.Entry<String, Integer> entry : animalDuplicates.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
