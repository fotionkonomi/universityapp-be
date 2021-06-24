package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository to make the interactions to the database that relate to the courses
 */
@Repository
public interface CourseRepository extends ParentRepository<Course, Long> {
    /**
     * Returns all courses that a specific student is enrolled in
     * Data returned will be pageable
     * @param id
     * @param pageable
     * @return
     */
    List<Course> findAllByStudentsEnrolled_Id(Long id, Pageable pageable);

    /**
     * Returns all courses that a specific student is enrolled in
     * @param id
     * @return
     */
    List<Course> findAllByStudentsEnrolled_Id(Long id);

    /**
     * Finds all courses that are available for a user to enroll in
     * @param id
     * @param pageable
     * @return
     */
    @Query("select c from Course c WHERE c not in (SELECT c FROM Course INNER JOIN c.studentsEnrolled s WHERE s.id = :id) and c.startDateTime > CURRENT_TIMESTAMP ")
    List<Course> findAllAvailableCourses(@Param("id") long id, Pageable pageable);

}
