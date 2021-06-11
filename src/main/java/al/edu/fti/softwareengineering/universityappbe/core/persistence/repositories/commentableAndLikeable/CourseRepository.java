package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends ParentRepository<Course, Long> {
    List<Course> findAllByStudentsEnrolled_Id(Long id, Pageable pageable);

    @Query("select c from Course c LEFT JOIN c.studentsEnrolled s where (s.id is null or s.id <> :id) and c.startDateTime > :startDateTime")
    List<Course> findAllAvailableCourses(@Param("id") long id, @Param("startDateTime") Date startDateTime, Pageable pageable);
}
