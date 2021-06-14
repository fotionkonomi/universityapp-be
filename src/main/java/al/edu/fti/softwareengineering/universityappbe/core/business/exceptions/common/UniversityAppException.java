package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common;

import lombok.Getter;
import lombok.Setter;

public abstract class UniversityAppException extends RuntimeException {
    @Getter
    @Setter
    private String messageKey;

    public UniversityAppException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }
}
