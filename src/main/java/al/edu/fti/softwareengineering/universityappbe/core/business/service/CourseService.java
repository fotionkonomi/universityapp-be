package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface CourseService extends BaseService<CourseDTO, Long> {
    List<CourseDTO> findAllByStudentEnrolled_id(Long id, int pageNumber);
}
