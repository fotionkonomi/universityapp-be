package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Stores information regarding a post
 * Maps the post table in the database
 */
@Data
@Entity
@Table(name = "post")
@SQLDelete(sql = "UPDATE post SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
public class Post extends CommentableAndLikeable {

    /**
     * Content of the post
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * User that has posted the post
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_posted_by", referencedColumnName = "id")
    private User postedBy;

    /**
     * If the post is created by making a comment, this field stores the comment from which this post is born
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_born_by_comment", referencedColumnName = "id")
    private Comment bornByComment;

    /**
     * If the post is created by an enrollment of a user to a course, this field stores the course where a user is enrolled
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_born_by_course_enrollment", referencedColumnName = "id")
    private Course bornByCourseEnrollment;
}
