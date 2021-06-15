package al.edu.fti.softwareengineering.universityappbe.core.business.dtos;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificationDTO extends BaseDTO {
    private UserDTO toUser;

    private boolean seen;

    private String content;

    private String parameters;
}
