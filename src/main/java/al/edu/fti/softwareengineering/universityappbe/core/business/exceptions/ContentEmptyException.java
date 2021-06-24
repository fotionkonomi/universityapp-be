package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

/**
 * Thrown if the content of a comment, or post is empty
 */
public class ContentEmptyException extends UniversityAppException {

    public ContentEmptyException() {
        super(MessageConstants.MSG_COMMENT_EMPTY_EXCEPTION);
    }
}
