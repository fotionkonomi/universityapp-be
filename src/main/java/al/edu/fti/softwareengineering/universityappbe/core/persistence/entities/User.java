package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Contains information of the users that use this application
 * Maps the user table in the database
 */
@Data
@Entity
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
@EqualsAndHashCode(callSuper = true, exclude = {"coursesOfAUser"})
public class User extends SoftDeletionEntity {

    /**
     * Stores the first name of the user
     */
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * Stores the last name of the user
     */
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * Stores the email of the user
     */
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    /**
     * Stores the password of the user
     */
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    /**
     * Stores the profile picture path of the user
     */
    @Column(name = "profile_picture_path", nullable = false)
    @Type(type="text")
    private String profilePicturePath;

    /**
     * Stores all the courses that a user is enrolled in
     * Makes a many to many relationship with the course table
     */
    @ManyToMany(mappedBy = "studentsEnrolled")
    private Set<Course> coursesOfAUser = new HashSet<>();

}
