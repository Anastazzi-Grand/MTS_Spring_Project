package ru.mts.hw8.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.hw8.entity.Animal;
import ru.mts.hw8.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    protected List<Animal> animals;

    @Autowired
    private CreateAnimalService animalService;

    @PostConstruct
    private void postConstruct(){
        animals = List.of(animalService.createTenUniqueAnimals());
    }

    @Override
    public String[] findLeapYearNames() {
        List<String> leapYearNames = new ArrayList<>();
        animals.forEach(animal -> {
            if (animal.getBirthDate().getYear() % 4 == 0) {
                leapYearNames.add(animal.getName());
            }
        });
        return leapYearNames.toArray(String[]::new);
    }

    @Override
    public Animal[] findOlderAnimal(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Age cannot be < 0");
        }
        LocalDate currentDate = LocalDate.now();
        return animals.stream()
                .filter(animal -> animal.getBirthDate().until(currentDate).getYears() > n)
                .toArray(Animal[]::new);
    }

    @Override
    public Set<String> findDuplicate() {
        Set<String> uniqueAnimals = new HashSet<>();
        Set<String> duplicateAnimals = new HashSet<>();
        animals.forEach(animal -> {
            if (!uniqueAnimals.add(animal.getName())) {
                duplicateAnimals.add(animal.getName());
            }
        });
        return duplicateAnimals;
    }

    @Override
    public void printDuplicate() {
        Set<String> duplicates = findDuplicate();
        if(duplicates.isEmpty()) {
            System.out.println("No duplicates found");
        } else {
            duplicates.forEach(animal -> System.out.println(animal + " is duplicate"));
        }
    }
}
