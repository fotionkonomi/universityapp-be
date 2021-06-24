package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Contains information of a hall where a course will take place
 * Maps the table hall in the database
 */
@Data
@Entity
@Table(name = "hall")
@SQLDelete(sql = "UPDATE hall SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
public class Hall extends SoftDeletionEntity {

    /**
     * Name of the hall
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * Coordination: latitude
     */
    @Column(nullable = false)
    private Double latitude;

    /**
     * Coordination: longitude
     */
    @Column(nullable = false)
    private Double longitude;

    /**
     * Description for a specific hall
     */
    @Column(nullable = false)
    private String description;

}
