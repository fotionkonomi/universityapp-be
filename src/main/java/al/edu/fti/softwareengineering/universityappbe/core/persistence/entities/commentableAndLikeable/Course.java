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
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
@SQLDelete(sql = "UPDATE course SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
@EqualsAndHashCode(callSuper = true, exclude = {"studentsEnrolled"})
public class Course extends CommentableAndLikeable {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date_time", updatable = false, columnDefinition = "DATETIME")
    private Date startDateTime;

    @Column(name = "repeat_count", nullable = false)
    private int repeatCount;

    @Column(name = "repeat_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CourseRepeatType repeatType;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "id_course") },
            inverseJoinColumns = { @JoinColumn(name = "id_student")})
    private Set<User> studentsEnrolled;

    public void addUser(User user) {
        this.studentsEnrolled.add(user);
        user.getCoursesOfAUser().add(this);
    }

    public void dropUser(User user) {
        this.studentsEnrolled.remove(user);
        user.getCoursesOfAUser().remove(this);
    }
}
