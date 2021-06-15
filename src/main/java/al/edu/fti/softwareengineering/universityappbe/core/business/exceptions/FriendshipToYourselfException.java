package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

public class FriendshipToYourselfException extends UniversityAppException {
    public FriendshipToYourselfException() {
        super(MessageConstants.MSG_FRIENDSHIP_SAME_USER);
    }
}
