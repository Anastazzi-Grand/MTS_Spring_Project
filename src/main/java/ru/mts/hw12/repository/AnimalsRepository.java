package ru.mts.hw12.repository;

import ru.mts.hw12.entity.Animal;
import ru.mts.hw12.exception.FindMinConstAnimalsException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public interface AnimalsRepository {

    /**
     * Нахождение животных, родившихся в високосный год
     *
     * @return Map, где ключ - тип животного + имя, значение - дата рождения животного
     */
    ConcurrentHashMap<String, LocalDate> findLeapYearNames();

    /**
     * Вывод животных, старше N лет
     *
     * @param n Значение возраста для поиска
     * @return Map, где ключ - животное, значение - возраст
     */
    ConcurrentHashMap<Animal, Integer> findOlderAnimal(int n);

    /**
     * Поиск дубликатов животных
     *
     * @return Map, где ключ - тип животного, значение - список с найденными дубликатами
     */
    ConcurrentHashMap<String, List<Animal>> findDuplicate();

    /**
     * Вывод дубликатов животных
     */
    void printDuplicate();

    /**
     * Поиск среднего возраста животных
     *
     * @param animalLists List животных
     */
    void findAverageAge(CopyOnWriteArrayList<Animal> animalLists);

    /**
     * Поиск животных, чей возраст больше 5 лет и цена выше средней
     *
     * @param animalLists List животных
     * @return List животных
     */
    CopyOnWriteArrayList<Animal> findOldAnimalExpensive(CopyOnWriteArrayList<Animal> animalLists);

    /**
     * Поиск 3 животных с минимальной ценой
     *
     * @param animalLists List животных
     * @return List животных, отсортированный в обратном алфавитном порядке
     */
    List<Animal> findMinConstAnimals(CopyOnWriteArrayList<Animal> animalLists) throws FindMinConstAnimalsException;
}
