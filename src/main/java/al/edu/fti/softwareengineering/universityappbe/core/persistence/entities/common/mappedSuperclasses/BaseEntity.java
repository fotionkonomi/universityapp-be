package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "DATETIME")
    private Date updatedAt;


    @PrePersist
    private void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }
}
