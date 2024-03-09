package ru.mts.hw10.repository;

import ru.mts.hw10.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {

    /**
     * Нахождение животных, родившихся в високосный год
     *
     * @return Map, где ключ - тип животного + имя, значение - дата рождения животного
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Вывод животных, старше N лет
     *
     * @param n Значение возраста для поиска
     * @return Map, где ключ - животное, значение - возраст
     */
    Map<Animal, Integer> findOlderAnimal(int n);

    /**
     * Поиск дубликатов животных
     *
     * @return Map, где ключ - тип животного, значение - список с найденными дубликатами
     */
    Map<String, List<Animal>> findDuplicate();

    /**
     * Вывод дубликатов животных
     */
    void printDuplicate();

    /**
     * Поиск среднего возраста животных
     *
     * @param animalLists List животных
     */
    void findAverageAge(List<Animal> animalLists);

    /**
     * Поиск животных, чей возраст больше 5 лет и цена выше средней
     *
     * @param animalLists List животных
     * @return List животных
     */
    List<Animal> findOldAnimalExpensive(List<Animal> animalLists);

    /**
     * Поиск 3 животных с минимальной ценой
     *
     * @param animalLists List животных
     * @return List животных, отсортированный в обратном алфавитном порядке
     */
    List<Animal> findMinConstAnimals(List<Animal> animalLists);
}
