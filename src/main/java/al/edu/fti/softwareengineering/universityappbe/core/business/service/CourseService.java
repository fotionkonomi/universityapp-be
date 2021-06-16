package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface CourseService extends BaseService<CourseDTO, Long> {
    List<CourseDTO> findAllByStudentEnrolled_id(Long id, int pageNumber);

    List<CourseDTO> findAllCoursesAvailableForLoggedUser(long id, int pageNumber);

    void saveUser(Long courseId, Long userId);

    void dropUser(Long courseId, Long userId);

    List<CourseDTO> getCoursesOfAFriend(Long idUserLogged, Long idFriend, int pageNumber);
}
