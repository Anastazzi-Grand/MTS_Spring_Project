package ru.mts.hw11.repository;

import org.springframework.stereotype.Repository;
import ru.mts.hw11.entity.Animal;
import ru.mts.hw11.service.CreateAnimalService;
import ru.mts.hw11.exception.EmptyListOfAnimalsException;
import ru.mts.hw11.exception.FindMinConstAnimalsException;
import ru.mts.hw11.exception.FindOlderAnimalException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private Map<String, List<Animal>> animals;

    private final CreateAnimalService animalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.animalService = createAnimalService;
    }


    @PostConstruct
    private void postConstruct(){
        animals = animalService.createTenUniqueAnimals();
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        return animals.values()
                .stream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(animal -> animal.getBreed() + " " + animal.getName(), Animal::getBirthDate, (r1, r2) -> r1));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        if (n < 0) {
            throw new FindOlderAnimalException("N should be > 0");
        }

        LocalDate currentDate = LocalDate.now();

        Map<Animal, Integer> olderAnimals = animals.values().stream()
                .flatMap(List::stream)
                .filter(animal -> Period.between(animal.getBirthDate(), currentDate).getYears() > n)
                .collect(Collectors.toMap(Function.identity(),
                        animal -> Period.between(animal.getBirthDate(), currentDate).getYears(),
                        Integer::max));

        if (olderAnimals.isEmpty()) {
            Optional<Map.Entry<Animal, Integer>> oldestAnimalEntry = animals.values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toMap(Function.identity(),
                            animal -> Period.between(animal.getBirthDate(), currentDate).getYears(),
                            Integer::max))
                    .entrySet().stream()
                    .max(Comparator.comparingInt(Map.Entry::getValue));

            oldestAnimalEntry.ifPresent(entry -> olderAnimals.put(entry.getKey(), entry.getValue()));
        }

        return olderAnimals;
    }


    @Override
    public Map<String, List<Animal>> findDuplicate() {
        List<Animal> animalsList = animals.values().stream().flatMap(List::stream).collect(Collectors.toList());

        return animals.values().stream()
                .flatMap(List::stream)
                .filter(animal -> Collections.frequency(animalsList, animal) > 1)
                .distinct()
                .collect(Collectors.groupingBy(Animal::getBreed));
    }

    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }
        animalDuplicates.forEach((key, value) -> System.out.println(key + "=" + value));
    }

    @Override
    public void findAverageAge(List<Animal> animalLists) {
        if (animalLists == null || animalLists.isEmpty()) {
            throw new EmptyListOfAnimalsException("List of animals is null or empty!");
        }
        double averageAge = animalLists.stream()
                .mapToInt(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                .average()
                .orElse(0.0);

        System.out.println("Средний возраст животных: " + averageAge + " лет");
    }

    @Override
    public List<Animal> findOldAnimalExpensive(List<Animal> animalLists) {
        if (animalLists == null || animalLists.isEmpty()) {
            throw new EmptyListOfAnimalsException("List of animals is null or empty!");
        }
        BigDecimal averagePrice = animalLists.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animalLists.size()), RoundingMode.HALF_UP);

        return animalLists.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5 &&
                        animal.getCost().compareTo(averagePrice) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> findMinConstAnimals(List<Animal> animalLists) {
        if (animalLists == null || animalLists.isEmpty()) {
            throw new EmptyListOfAnimalsException("List of animals is null or empty!");
        } else if (animalLists.size() < 3) {
            throw new FindMinConstAnimalsException("Size of list of animals should be >= 3");
        }
        return animalLists.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .collect(Collectors.toList());
    }
}
