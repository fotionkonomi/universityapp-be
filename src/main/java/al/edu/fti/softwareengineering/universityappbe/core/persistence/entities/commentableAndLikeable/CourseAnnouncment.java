package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Stores information regarding an announcement in a course
 * Maps the announcment table in the database
 */
@Data
@Entity
@Table(name = "announcment")
@SQLDelete(sql = "UPDATE announcment SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
@EqualsAndHashCode(exclude = {"likes", "commentedContent"})
public class CourseAnnouncment extends CommentableAndLikeable {

    /**
     * Stores the course where the announcement will be posted
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_course", referencedColumnName = "id")
    private Course courseField;

    /**
     * Content of the announcement
     */
    @Column(nullable = false)
    private String content;
}
