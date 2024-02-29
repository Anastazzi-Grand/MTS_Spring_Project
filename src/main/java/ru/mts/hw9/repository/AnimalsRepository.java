package ru.mts.hw9.repository;

import ru.mts.hw9.entity.Animal;

import java.time.LocalDate;
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
     * @return Map, где ключ - тип животного, значение - кол-во найденных дублей
     */
    Map<String, Integer> findDuplicate();

    /**
     * Вывод дубликатов животных
     */
    void printDuplicate();
}
