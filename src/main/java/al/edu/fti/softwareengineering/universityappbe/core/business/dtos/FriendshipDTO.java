package al.edu.fti.softwareengineering.universityappbe.core.business.dtos;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.BaseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.SoftDeletionDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class FriendshipDTO extends BaseDTO {
    private UserDTO requestedBy;
    private UserDTO requestedTo;
    private Boolean active;
}
