package ru.zetta.tariff.selector.exception;

public class UnmarshalException extends Exception {
    public UnmarshalException() {
    }

    public UnmarshalException(String message) {
        super(message);
    }

    public UnmarshalException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnmarshalException(Throwable cause) {
        super(cause);
    }
}
