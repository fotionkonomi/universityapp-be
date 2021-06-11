package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CourseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl extends AbstractJpaService<CourseDTO, Course, Long> implements CourseService {

    public CourseServiceImpl() {
        super(Course.class, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> findAllByStudentEnrolled_id(Long id, int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        return mapEntityListToDTO(getCourseRepository().findAllByStudentsEnrolled_Id(id, PageRequest.of(pageNumber, 10)));
    }

    @Override
    public List<CourseDTO> findAllCoursesAvailableForLoggedUser(long id, int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        return mapEntityListToDTO(getCourseRepository().findAllAvailableCourses(id, new Date(), PageRequest.of(pageNumber, 10)));
    }

    private CourseRepository getCourseRepository() {
        return (CourseRepository) repo;
    }
}
