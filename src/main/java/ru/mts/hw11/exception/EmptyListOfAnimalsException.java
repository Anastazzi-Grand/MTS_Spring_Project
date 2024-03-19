package ru.mts.hw11.exception;

public class EmptyListOfAnimalsException extends IllegalArgumentException {
    public EmptyListOfAnimalsException(String message) {
        super(message);
    }
}
