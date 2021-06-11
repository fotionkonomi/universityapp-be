package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Data
@MappedSuperclass
public class SoftDeletionEntity extends BaseEntity {

    @Column(nullable = false, name = "deleted")
    private Boolean deleted;

    @Override
    protected void prePersist() {
        super.prePersist();
        this.deleted = false;
    }

    @Override
    protected void preUpdate() {
        super.preUpdate();
        if (this.deleted == null) {
            this.deleted = false;
        }
    }
}
