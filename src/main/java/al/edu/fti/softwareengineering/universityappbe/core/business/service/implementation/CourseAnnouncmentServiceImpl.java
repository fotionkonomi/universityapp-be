package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseAnnouncmentService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.CourseAnnouncment;
import org.springframework.stereotype.Service;

@Service
public class CourseAnnouncmentServiceImpl extends AbstractJpaService<CourseAnnouncmentDTO, CourseAnnouncment, Long> implements CourseAnnouncmentService {
    public CourseAnnouncmentServiceImpl() {
        super(CourseAnnouncment.class, CourseAnnouncmentDTO.class);
    }
}
