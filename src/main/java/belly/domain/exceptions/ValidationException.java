package belly.domain.exceptions;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 4302086619213682483L;

    public ValidationException(String message) {
        super(message);
    }
}
