package al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.CommentableAndLikeableDTO;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostDTO extends CommentableAndLikeableDTO {
    private String content;

    private UserDTO postedBy;

    private Comment bornByComment;

    private CourseDTO bornByCourseEnrollment;
}
