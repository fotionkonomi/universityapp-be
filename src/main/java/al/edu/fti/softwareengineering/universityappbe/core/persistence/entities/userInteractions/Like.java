package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.UserInteraction;
import lombok.Data;

import javax.persistence.*;

/**
 * This object will be mapped to the likeInteraction table in the database
 */
@Data
@Entity
@Table(name = "likeInteraction")
public class Like extends UserInteraction {

    /**
     * Possible information regarding the comment that is liked
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_comment_liked", referencedColumnName = "id")
    private Comment commentLiked;

    /**
     * Possible information regarding the content that is liked
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "liked_content")
    private CommentableAndLikeable likedContent;
}
