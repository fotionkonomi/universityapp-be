package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

public class EmailViolationException extends UniversityAppException {
    public EmailViolationException() {
        super(MessageConstants.MSG_EMAIL_EXISTS);
    }
}
