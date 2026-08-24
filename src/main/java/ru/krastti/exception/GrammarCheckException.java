package ru.krastti.exception;

public class GrammarCheckException extends RuntimeException {

    public GrammarCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public GrammarCheckException(String message) {
        super(message);
    }
}
