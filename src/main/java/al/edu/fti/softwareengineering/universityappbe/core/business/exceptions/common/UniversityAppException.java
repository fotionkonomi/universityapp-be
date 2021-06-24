package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Superclass of all custom exceptions of the application
 */
public abstract class UniversityAppException extends RuntimeException {

    /**
     * Stores the message that will be sent to the consumer of our web services
     */
    @Getter
    @Setter
    private String messageKey;

    public UniversityAppException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }
}
