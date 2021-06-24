package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Like;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * This class is the superclass of all contents that can be commented and liked
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CommentableAndLikeable extends SoftDeletionEntity {

    /**
     * Stores all the likes pf a specific content
     */
    @OneToMany(mappedBy = "likedContent")
    private List<Like> likes;

    /**
     * Stores all the comments of a specific content
     */
    @OneToMany(mappedBy = "commentedContent")
    private List<Comment> comments;
}
