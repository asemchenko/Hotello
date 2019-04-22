package example.company.model.service.exceptions;

public class EmailAlreadyTakenException extends Exception {
    public EmailAlreadyTakenException() {
    }

    public EmailAlreadyTakenException(String message) {
        super(message);
    }

    public EmailAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyTakenException(Throwable cause) {
        super(cause);
    }

    public EmailAlreadyTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
