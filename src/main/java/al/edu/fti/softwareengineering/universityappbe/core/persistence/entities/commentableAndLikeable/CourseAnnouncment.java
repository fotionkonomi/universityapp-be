package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "announcment")
@SQLDelete(sql = "UPDATE announcment SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
public class CourseAnnouncment extends CommentableAndLikeable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_course", referencedColumnName = "id")
    private Course course;

    @Column(nullable = false)
    private String content;
}
