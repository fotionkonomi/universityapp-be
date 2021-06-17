package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

public class CommentEmptyException extends UniversityAppException {

    public CommentEmptyException() {
        super(MessageConstants.MSG_COMMENT_EMPTY_EXCEPTION);
    }
}
