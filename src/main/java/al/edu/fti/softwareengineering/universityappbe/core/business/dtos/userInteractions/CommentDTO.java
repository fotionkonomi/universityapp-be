package al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.CommentableAndLikeableDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.UserInteractionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentDTO extends UserInteractionDTO {
    private String content;

    private CommentableAndLikeableDTO commentedContent;
}
