package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Hall;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.enums.CourseRepeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

/**
 * Stores information regarding the courses
 * Maps the course table in the database
 */
@Data
@Entity
@Table(name = "course")
@SQLDelete(sql = "UPDATE course SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
@EqualsAndHashCode(callSuper = true, exclude = {"studentsEnrolled", "likes", "commentedContent"})
public class Course extends CommentableAndLikeable {

    /**
     * Name of the course
     */
    @Column(nullable = false)
    private String name;

    /**
     * Description of the course
     */
    @Column(nullable = false)
    private String description;

    /**
     * Hall where this course is taking place
     * Makes a Many To One relationship with the hall table
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    /**
     * Start date and time of the course
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date_time", updatable = false, columnDefinition = "DATETIME")
    private Date startDateTime;

    /**
     * Number of times this course will take place
     */
    @Column(name = "repeat_count", nullable = false)
    private int repeatCount;

    /**
     * Specifies the way of repetition of this course, in our case: daily, weekly, monthly
     */
    @Column(name = "repeat_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CourseRepeatType repeatType;

    /**
     * Stores all the users enrolled in this course
     * Makes a many to many relationship with the user table
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "id_course") },
            inverseJoinColumns = { @JoinColumn(name = "id_student")})
    private Set<User> studentsEnrolled = new HashSet<>();

    /**
     * Stores all the announcements of a course
     * Makes a one to many relationship with the announcement table
     */
    @OneToMany(mappedBy = "courseField")
    private List<CourseAnnouncment> courseAnnouncments = new ArrayList<>();

    /**
     * Adds a user in the many to many relationship betweeen the users and the courses
     * @param user
     */
    public void addUser(User user) {
        this.studentsEnrolled.add(user);
        user.getCoursesOfAUser().add(this);
    }

    /**
     * Deletes a user in the many to many relationship between the users and the courses
     * @param user
     */
    public void dropUser(User user) {
        this.studentsEnrolled.remove(user);
        user.getCoursesOfAUser().remove(this);
    }
}
