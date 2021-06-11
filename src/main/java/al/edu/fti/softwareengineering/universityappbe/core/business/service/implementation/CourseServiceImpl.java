package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EntityNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.UserRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl extends AbstractJpaService<CourseDTO, Course, Long> implements CourseService {

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public void saveUser(Long courseId, Long userId) {
        Course course = prepareCourseForAddOrDrop(courseId);
        User user = prepareUserForAddOrDrop(userId);

        course.addUser(user);
        repo.save(course);
    }

    @Override
    public void dropUser(Long courseId, Long userId) {
        Course course = prepareCourseForAddOrDrop(courseId);
        User user = prepareUserForAddOrDrop(userId);

        course.dropUser(user);
        repo.save(course);
    }

    private CourseRepository getCourseRepository() {
        return (CourseRepository) repo;
    }

    private Course prepareCourseForAddOrDrop(Long courseId) {
        Optional<Course> optionalCourse = this.repo.findById(courseId);
        Course course = null;
        if(optionalCourse.isPresent()) {
            course = optionalCourse.get();
        } else {
            throw new EntityNotFoundException();
        }
        return course;
    }

    private User prepareUserForAddOrDrop(Long userId) {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        User user = null;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new EntityNotFoundException();
        }
        return user;
    }
}
