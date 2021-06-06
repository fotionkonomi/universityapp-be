package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "DATETIME")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "DATETIME")
    private Date updatedAt;

    @Column(nullable = false, name = "deleted")
    private Boolean deleted;

    @PrePersist
    private void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.deleted = false;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = new Date();
        if (this.deleted == null) {
            this.deleted = false;
        }
    }
}
