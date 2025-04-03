package Exceptions;

public class WordAlreadyExistsException extends Exception {

    public WordAlreadyExistsException(String message) {
        super(message);
    }

    public WordAlreadyExistsException() {

    }
}
