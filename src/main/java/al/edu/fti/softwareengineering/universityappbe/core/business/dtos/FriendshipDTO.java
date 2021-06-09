package al.edu.fti.softwareengineering.universityappbe.core.business.dtos;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.SoftDeletionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FriendshipDTO extends SoftDeletionDTO {
    private UserDTO requestedBy;
    private UserDTO requestedTo;
    private Boolean active;
}
