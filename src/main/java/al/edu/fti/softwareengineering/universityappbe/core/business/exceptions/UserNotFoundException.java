package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

public class UserNotFoundException extends UniversityAppException {
    public UserNotFoundException() {
        super(MessageConstants.MSG_USER_NOT_FOUND_EXCEPTION);
    }
}
