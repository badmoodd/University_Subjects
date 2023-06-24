package org.example.Processor.Memory.Exceptions;

public class MemoryException extends Exception{
    public MemoryException() {
    }

    public MemoryException(String message) {
        super(message);
    }

    public MemoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemoryException(Throwable cause) {
        super(cause);
    }

    public MemoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
