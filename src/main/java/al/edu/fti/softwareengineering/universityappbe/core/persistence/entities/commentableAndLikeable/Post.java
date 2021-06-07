package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
@SQLDelete(sql = "UPDATE post SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
public class Post extends CommentableAndLikeable {

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_posted_by", referencedColumnName = "id")
    private User postedBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_born_by_comment", referencedColumnName = "id")
    private Comment bornByComment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_born_by_course_enrollment", referencedColumnName = "id")
    private Course bornByCourseEnrollment;
}
