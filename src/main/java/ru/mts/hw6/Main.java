package ru.mts.hw6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.hw6.config.Config;
import ru.mts.hw6.entity.Animal;
import ru.mts.hw6.repository.AnimalsRepository;
import ru.mts.hw6.service.CreateAnimalService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);
        CreateAnimalService createAnimalService = context.getBean(CreateAnimalService.class);
        System.out.println(createAnimalService.getAnimalType());

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
