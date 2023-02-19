package com.practiceSpring.Spring.Tutorial.error;

public class DepartementNotFoundExcepition extends Exception{
    public DepartementNotFoundExcepition(String message) {
        super(message);
    }

    public DepartementNotFoundExcepition(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartementNotFoundExcepition(Throwable cause) {
        super(cause);
    }

    protected DepartementNotFoundExcepition(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
