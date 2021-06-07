package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class UserInteraction extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "interacted_by")
    private User interactedBy;
}
