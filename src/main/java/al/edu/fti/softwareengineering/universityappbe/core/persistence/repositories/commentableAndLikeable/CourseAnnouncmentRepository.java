package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.CourseAnnouncment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseAnnouncmentRepository extends ParentRepository<CourseAnnouncment, Long> {
    List<CourseAnnouncment> findAllByCourseField_Id(Long idCourse);
}
