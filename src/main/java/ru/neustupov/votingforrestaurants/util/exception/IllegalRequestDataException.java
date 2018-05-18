package ru.neustupov.votingforrestaurants.util.exception;

public class IllegalRequestDataException extends RuntimeException{
    public IllegalRequestDataException(String msg) {
        super(msg);
    }
}
