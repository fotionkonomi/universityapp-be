package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *  This is the superclass for the entities of the application.
 *   We use this object to achieve a better code reusage and make generic repositories easier
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * This field will work as a primary key for all entities in the application
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    /**
     * This field will contain the datetime when a row is added in the database
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME")
    private Date createdAt;

    /**
     * This field will contain the datetime when a row is updated in the database
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "DATETIME")
    private Date updatedAt;


    /**
     * This method will be executed before every persist.
     * Here we save the timestamp for the createdAt and updatedAt fields
     */
    @PrePersist
    protected void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    /**
     * This method will be executed before every update of an exisiting entity.
     * Here we only update the timestamp for the updatedAt field
     */
    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }
}
