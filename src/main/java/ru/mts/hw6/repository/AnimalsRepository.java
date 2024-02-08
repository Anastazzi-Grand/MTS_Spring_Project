package ru.mts.hw6.repository;

import ru.mts.hw6.entity.Animal;

import java.util.Set;

public interface AnimalsRepository {

    /**
     * Нахождение животных, родившихся в високосный год
     *
     * @return Массив из имен животных
     */
    String[] findLeapYearNames();

    /**
     * Вывод животных, старше N лет
     *
     * @param n Значение возраста для поиска
     * @return Массив подходящих животных
     */
    Animal[] findOlderAnimal(int n);

    /**
     * Поиск дубликатов животных
     */
    Set<Animal> findDuplicate();

    /**
     * Вывод дубликатов животных
     */
    void printDuplicate();
}
