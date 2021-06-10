package al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import lombok.Data;

@Data
public abstract class UserInteractionDTO extends BaseDTO {
    private UserDTO interactedBy;
}
