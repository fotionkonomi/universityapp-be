package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

public class FriendshipNotFoundException extends UniversityAppException {

    public FriendshipNotFoundException() {
        super(MessageConstants.MSG_FRIENDSHIP_NOT_FOUND);
    }
}
