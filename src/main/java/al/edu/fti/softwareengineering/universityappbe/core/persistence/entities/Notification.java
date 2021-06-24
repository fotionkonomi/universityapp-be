package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Stores information regarding notifications
 * Maps the notification table in the database
 */
@Entity
@Data
@Table(name = "notification")
public class Notification extends BaseEntity {

    /**
     * Information of the user that will receive the notification
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "to_user")
    private User toUser;

    /**
     * Distinguishes if a notification has been seen or not by the user
     */
    private boolean seen;

    /**
     * Message that will be sent with the notification
     */
    @Column(nullable = false)
    private String content;

    /**
     * Optional parameters that a message can have
     */
    private String parameters;

}
