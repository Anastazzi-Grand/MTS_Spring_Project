package ru.mts.hw7.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.hw7.entity.Animal;
import ru.mts.hw7.service.CreateAnimalService;

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
        int currentYear = Year.now().getValue();
        animals.forEach(animal -> {
            if (animal.getBirthDate().getYear() % 4 == 0) {
                leapYearNames.add(animal.getName());
            }
        });
        return leapYearNames.toArray(String[]::new);
    }

    @Override
    public Animal[] findOlderAnimal(int n) {
        LocalDate currentDate = LocalDate.now();
        return animals.stream()
                .filter(animal -> animal.getBirthDate().until(currentDate).getYears() > n)
                .toArray(Animal[]::new);
    }

    @Override
    public Set<Animal> findDuplicate() {
        Set<Animal> uniqueAnimals = new HashSet<>();
        Set<Animal> duplicateAnimals = new HashSet<>();
        animals.forEach(animal -> {
            if (!uniqueAnimals.add(animal)) {
                duplicateAnimals.add(animal);
            }
        });
        return duplicateAnimals;
    }

    @Override
    public void printDuplicate() {
        Set<Animal> duplicates = findDuplicate();
        if(duplicates.isEmpty()) {
            System.out.println("Дубликаты не найдены");
        } else {
            duplicates.forEach(animal -> System.out.println(animal.getName() + " дубликат"));
        }
    }
}
