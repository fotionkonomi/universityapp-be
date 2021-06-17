package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Like;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CommentableAndLikeable extends SoftDeletionEntity {

    @OneToMany(mappedBy = "likedContent")
    private List<Like> likes;

    @OneToMany(mappedBy = "commentedContent")
    private List<Comment> comments;
}
