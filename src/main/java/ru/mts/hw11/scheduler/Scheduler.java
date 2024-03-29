package ru.mts.hw11.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw11.entity.Animal;
import ru.mts.hw11.exception.EmptyListOfAnimalsException;
import ru.mts.hw11.exception.FindMinConstAnimalsException;
import ru.mts.hw11.exception.FindOlderAnimalException;
import ru.mts.hw11.repository.AnimalsRepositoryImpl;

import java.util.List;

@Component
@EnableScheduling
public class Scheduler {
    private final AnimalsRepositoryImpl animalsRepository;

    public Scheduler(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }


    @Scheduled(fixedRate = 60000)
    public void printEveryMinute() {
        System.out.println("Duplicates:");
        animalsRepository.printDuplicate();
        System.out.println("Leap year animals:");
        System.out.println(animalsRepository.findLeapYearNames());
        System.out.println("Animals older then 20:");
        System.out.println(animalsRepository.findOlderAnimal(20));
        System.out.println();
        System.out.println("###############################");

        List<Animal> animals = null;
        try {
            System.out.println(animalsRepository.findMinConstAnimals(animals));
        } catch (FindMinConstAnimalsException e) {
            System.out.println(e);
        }

        try {
            animalsRepository.findAverageAge(animals);
        } catch (EmptyListOfAnimalsException e) {
            System.out.println(e);
        }

        try {
            System.out.println(animalsRepository.findOldAnimalExpensive(animals));
        } catch (EmptyListOfAnimalsException e) {
            System.out.println(e);
        }

        try {
            System.out.println(animalsRepository.findOlderAnimal(-1));
        } catch (FindOlderAnimalException e) {
            System.out.println(e);
        }
    }
}
