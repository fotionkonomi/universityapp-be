package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.UserInteraction;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "likeInteraction")
public class Like extends UserInteraction {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_comment_liked", referencedColumnName = "id")
    private Comment commentLiked;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "liked_content")
    private CommentableAndLikeable likedContent;
}
