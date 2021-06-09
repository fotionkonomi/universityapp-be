package al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common;

import lombok.Data;

@Data
public abstract class SoftDeletionDTO extends BaseDTO {
    private Boolean deleted;
}
