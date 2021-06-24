package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface CourseService extends BaseService<CourseDTO, Long> {

    /**
     * Finds all the courses where a user is enrolled in
     * Data returned in a pageable way
     * @param id
     * @param pageNumber
     * @return
     */
    List<CourseDTO> findAllByStudentEnrolled_id(Long id, int pageNumber);

    /**
     * Finds all the courses where a user is enrolled in
     * @param id
     * @return
     */
    List<CourseDTO> findAllByStudentEnrolled_id(Long id);

    /**
     * Finds all that are available for a user to be enrolled in
     * Data is returned in a pageable way
     * @param id
     * @param pageNumber
     * @return
     */
    List<CourseDTO> findAllCoursesAvailableForLoggedUser(long id, int pageNumber);

    void saveUser(Long courseId, Long userId);

    void dropUser(Long courseId, Long userId);

    /**
     * Returns all the courses that a user is enrolled in, only if the two users are already friends
     * @param idUserLogged
     * @param idFriend
     * @param pageNumber
     * @return
     */
    List<CourseDTO> getCoursesOfAFriend(Long idUserLogged, Long idFriend, int pageNumber);
}
