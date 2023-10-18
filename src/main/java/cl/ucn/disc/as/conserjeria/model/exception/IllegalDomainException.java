package cl.ucn.disc.as.conserjeria.model.exception;

/**
 * Domain Violation Exception
 *
 * @author Arquitectura de software
 */
public class IllegalDomainException extends RuntimeException {

    /**
     * The constructor
     *
     * @param message to use
     */
    public IllegalDomainException(String message) {
        super(message);
    }
}