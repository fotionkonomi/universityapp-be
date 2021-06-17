package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseAnnouncmentService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.CourseAnnouncment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CourseAnnouncmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseAnnouncmentServiceImpl extends AbstractJpaService<CourseAnnouncmentDTO, CourseAnnouncment, Long> implements CourseAnnouncmentService {
    public CourseAnnouncmentServiceImpl() {
        super(CourseAnnouncment.class, CourseAnnouncmentDTO.class);
    }

    @Override
    public List<CourseAnnouncmentDTO> announcmentsOfACourse(Long idCourse) {
        List<CourseAnnouncment> courseAnnouncments = getCourseAnnouncementRepository().findAllByCourseField_Id(idCourse);
        return mapEntityListToDTO(courseAnnouncments);
    }

    public CourseAnnouncmentRepository getCourseAnnouncementRepository() {
        return (CourseAnnouncmentRepository) repo;
    }
}
