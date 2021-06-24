package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.CourseAnnouncment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to make the interactions to the database that relate to the course announcements
 */
@Repository
public interface CourseAnnouncmentRepository extends ParentRepository<CourseAnnouncment, Long> {
    /**
     * Finds all announcements in a course
     * @param idCourse
     * @return
     */
    List<CourseAnnouncment> findAllByCourseField_Id(Long idCourse);
}
