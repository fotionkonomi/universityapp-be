package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends AbstractJpaService<CourseDTO, Course, Long> implements CourseService {
    public CourseServiceImpl() {
        super(Course.class, CourseDTO.class);
    }
}
