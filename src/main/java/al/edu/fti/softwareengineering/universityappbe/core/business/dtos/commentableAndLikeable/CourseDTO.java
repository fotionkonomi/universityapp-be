package al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.HallDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.CommentableAndLikeableDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.enums.CourseRepeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseDTO extends CommentableAndLikeableDTO {
    private String name;

    private String description;

    private HallDTO hall;

    private Date startDateTime;

    private int repeatCount;

    private CourseRepeatType repeatType;
}
