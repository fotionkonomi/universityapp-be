package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface CourseAnnouncmentService extends BaseService<CourseAnnouncmentDTO, Long> {
    List<CourseAnnouncmentDTO> announcmentsOfACourse(Long idCourse);
}
