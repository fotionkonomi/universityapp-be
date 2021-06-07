package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
public class User extends SoftDeletionEntity {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "profile_picture_path", nullable = false)
    private String profilePicturePath;

    @ManyToMany(mappedBy = "studentsEnrolled")
    private Set<Course> coursesOfAUser;

}
