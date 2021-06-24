package al.edu.fti.softwareengineering.universityappbe.core.business.exceptions;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.common.UniversityAppException;

/**
 * Thrown if an entity is not found in the database
 */
public class EntityNotFoundException extends UniversityAppException {

    public EntityNotFoundException() {
        super(MessageConstants.MSG_ENTITY_NOT_FOUND_EXCEPTION);
    }
}
