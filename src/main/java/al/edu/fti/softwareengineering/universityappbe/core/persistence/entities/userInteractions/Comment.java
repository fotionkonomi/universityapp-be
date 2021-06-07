package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.UserInteraction;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "comment")
public class Comment extends UserInteraction {

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "commentLiked")
    private List<Like> likesOfComment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "commented_content")
    private CommentableAndLikeable commentedContent;
}
