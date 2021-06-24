package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *  This is a class that will be extended by all classes where soft deletion will be available
 */
@Data
@MappedSuperclass
public class SoftDeletionEntity extends BaseEntity {

    /**
     *  This field will contain the information if an entity is deleted or not
     */
    @Column(nullable = false, name = "deleted")
    private Boolean deleted;

    /**
     * This method will be executed before creating any new object.
     * It will save the timestamps and it will also set the deleted field to false, because a new object cannot be a deleted object
     */
    @Override
    protected void prePersist() {
        super.prePersist();
        this.deleted = false;
    }

    /**
     * This method will be executed before the update of any object
     */
    @Override
    protected void preUpdate() {
        super.preUpdate();
        if (this.deleted == null) {
            this.deleted = false;
        }
    }
}
