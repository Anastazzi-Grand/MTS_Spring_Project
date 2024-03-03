package ru.mts.hw9.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw9.repository.AnimalsRepositoryImpl;

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
        System.out.println("Animals older then 10:");
        System.out.println(animalsRepository.findOlderAnimal(10));
        System.out.println("###############################");
    }
}
