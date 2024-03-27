package ru.mts.hw12.repository;

import org.springframework.stereotype.Repository;
import ru.mts.hw12.entity.Animal;
import ru.mts.hw12.service.CreateAnimalService;
import ru.mts.hw12.exception.EmptyListOfAnimalsException;
import ru.mts.hw12.exception.FindMinConstAnimalsException;
import ru.mts.hw12.exception.FindOlderAnimalException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private ConcurrentHashMap<String, List<Animal>> animals;

    private final CreateAnimalService animalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.animalService = createAnimalService;
    }



    @PostConstruct
    private void postConstruct(){
        animals = new ConcurrentHashMap<>(animalService.createTenUniqueAnimals());
    }

    @Override
    public ConcurrentHashMap<String, LocalDate> findLeapYearNames() {
        return (ConcurrentHashMap<String, LocalDate>) animals.values()
                .stream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toConcurrentMap(animal -> animal.getBreed() + " " + animal.getName(), Animal::getBirthDate, (r1, r2) -> r1));
    }

    @Override
    public ConcurrentHashMap<Animal, Integer> findOlderAnimal(int n) {
        if (n < 0) {
            throw new FindOlderAnimalException("N should be > 0");
        }

        LocalDate currentDate = LocalDate.now();

        ConcurrentHashMap<Animal, Integer> olderAnimals = (ConcurrentHashMap<Animal, Integer>) animals.values().stream()
                .flatMap(List::stream)
                .filter(animal -> Period.between(animal.getBirthDate(), currentDate).getYears() > n)
                .collect(Collectors.toConcurrentMap(Function.identity(),
                        animal -> Period.between(animal.getBirthDate(), currentDate).getYears(),
                        Integer::max));

        if (olderAnimals.isEmpty()) {
            Optional<Map.Entry<Animal, Integer>> oldestAnimalEntry = animals.values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toConcurrentMap(Function.identity(),
                            animal -> Period.between(animal.getBirthDate(), currentDate).getYears(),
                            Integer::max))
                    .entrySet().stream()
                    .max(Comparator.comparingInt(Map.Entry::getValue));

            oldestAnimalEntry.ifPresent(entry -> olderAnimals.put(entry.getKey(), entry.getValue()));
        }

        return olderAnimals;
    }


    @Override
    public ConcurrentHashMap<String, List<Animal>> findDuplicate() {
        ConcurrentHashMap<String, List<Animal>> animalsMap = new ConcurrentHashMap<>();

        CopyOnWriteArrayList<Animal> animalsList = new CopyOnWriteArrayList<>(animals.values().stream().flatMap(List::stream).collect(Collectors.toList()));

        for (Animal animal : animalsList) {
            String key = animal.getBreed();
            animalsMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(animal);
        }

        return animalsMap;
    }

    @Override
    public void printDuplicate() {
        ConcurrentHashMap<String, List<Animal>> animalDuplicates = findDuplicate();
        if (animalDuplicates.isEmpty()) {
            System.out.println("There is no duplicates");
        }
        animalDuplicates.forEach((key, value) -> System.out.println(key + "=" + value));
    }

    @Override
    public void findAverageAge(CopyOnWriteArrayList<Animal> animalLists) {
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
    public CopyOnWriteArrayList<Animal> findOldAnimalExpensive(CopyOnWriteArrayList<Animal> animalLists) {
        if (animalLists == null || animalLists.isEmpty()) {
            throw new EmptyListOfAnimalsException("List of animals is null or empty!");
        }
        BigDecimal averagePrice = animalLists.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animalLists.size()), RoundingMode.HALF_UP);

        return new CopyOnWriteArrayList<>(animalLists.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5 &&
                        animal.getCost().compareTo(averagePrice) > 0)
                .collect(Collectors.toList()));
    }

    @Override
    public CopyOnWriteArrayList<Animal> findMinConstAnimals(CopyOnWriteArrayList<Animal> animalLists) throws FindMinConstAnimalsException {
        if (animalLists == null) {
            throw new FindMinConstAnimalsException("List of animals is null or empty!");
        } else if (animalLists.size() < 3) {
            throw new FindMinConstAnimalsException("Size of list of animals should be >= 3");
        }
        return new CopyOnWriteArrayList<>(animalLists.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .collect(Collectors.toList()));
    }
}
