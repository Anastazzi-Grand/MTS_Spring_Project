package ru.mts.hw8.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw8.entity.Animal;
import ru.mts.hw8.repository.AnimalsRepositoryImpl;

@Component
@EnableScheduling
public class Scheduler {
    private final AnimalsRepositoryImpl animalsRepository;

    public Scheduler(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }


    @Scheduled(fixedRate = 60000)
    public void printEveryMinute() {
        System.out.println("Нахождение имен животных, родившихся в високосный год:");
        String[] arr1 = animalsRepository.findLeapYearNames();
        for(String animal : arr1){
            System.out.println(animal);
        }

        System.out.println("Нахождение животных, которым больше 10 лет:");
        Animal[] arr2 = animalsRepository.findOlderAnimal(10);
        for( Animal animal : arr2 ){
            System.out.println(animal);
        }

        System.out.println("Вывод дупликатов:");
        animalsRepository.printDuplicate();
    }
}
