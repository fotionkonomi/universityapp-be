package al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController extends CommonCrudRestController<CourseDTO, Long> {

}
