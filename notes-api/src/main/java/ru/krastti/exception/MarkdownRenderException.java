package ru.krastti.exception;

public class MarkdownRenderException extends RuntimeException {

    public MarkdownRenderException(String message) {
        super(message);
    }

    public MarkdownRenderException(String message, Throwable cause) {
        super(message, cause);
    }
}