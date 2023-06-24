package org.example.Exeptions;

public class LogicalFormulaException extends Exception {
    public LogicalFormulaException() {
    }

    public LogicalFormulaException(String message) {
        super(message);
    }

    public LogicalFormulaException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicalFormulaException(Throwable cause) {
        super(cause);
    }

    public LogicalFormulaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

