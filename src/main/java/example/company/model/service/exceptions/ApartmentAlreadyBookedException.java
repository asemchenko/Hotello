package example.company.model.service.exceptions;

public class ApartmentAlreadyBookedException extends Exception {
    public ApartmentAlreadyBookedException() {
    }

    public ApartmentAlreadyBookedException(String message) {
        super(message);
    }

    public ApartmentAlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApartmentAlreadyBookedException(Throwable cause) {
        super(cause);
    }

    public ApartmentAlreadyBookedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
