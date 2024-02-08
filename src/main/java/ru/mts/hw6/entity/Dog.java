package ru.mts.hw6.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Dog extends Pet {
    /**
     * Конструктор для создания собаки с заданным породой, именем и ДР
     * использует значения по умолчанию для стоимости и характера
     * @param breed порода собаки
     * @param name имя собаки
     * @param birthDate ДР
     */
    public Dog(String breed, String name, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = new BigDecimal("1131.14435").setScale(2, RoundingMode.HALF_UP);
        this.character = "Cute";
        this.birthDate = birthDate;
    }
}
