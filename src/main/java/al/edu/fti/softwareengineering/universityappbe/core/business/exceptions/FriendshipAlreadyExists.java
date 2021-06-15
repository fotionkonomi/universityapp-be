package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

public class FriendshipAlreadyExists extends UniversityAppException {

    public FriendshipAlreadyExists() {
        super(MessageConstants.MSG_FRIENDSHIP_ALREADY_EXISTS);
    }
}
