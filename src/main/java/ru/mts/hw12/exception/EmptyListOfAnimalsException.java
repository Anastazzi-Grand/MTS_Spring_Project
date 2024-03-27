package ru.mts.hw12.exception;

public class EmptyListOfAnimalsException extends IllegalArgumentException {
    public EmptyListOfAnimalsException(String message) {
        super(message);
    }
}
