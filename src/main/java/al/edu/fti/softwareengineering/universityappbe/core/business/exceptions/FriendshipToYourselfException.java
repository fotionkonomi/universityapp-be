package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

/**
 * Thrown if a user is trying to send a friendship to himself
 */
public class FriendshipToYourselfException extends UniversityAppException {
    public FriendshipToYourselfException() {
        super(MessageConstants.MSG_FRIENDSHIP_SAME_USER);
    }
}
