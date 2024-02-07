package ru.mts.HW6.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * интерфейс Animal с методами для получения информации о животных
 * */
public interface Animal {
    /**
     * Возвращает породу животного
     *
     * @return порода животного
     */
    String getBreed();

    /**
     * Возвращает имя животного
     *
     * @return имя животного
     */
    String getName();

    /**
     * Возвращает цену животного
     *
     * @return цена животного
     */
    BigDecimal getCost();

    /**
     * Возвращает характер животного
     *
     * @return характер животного
     */
    String getCharacter();

    /**
     * Возвращает ДР животного в формате dd-MM-yyyy
     *
     * @return ДР животного
     */
    LocalDate getBirthDate();
}
