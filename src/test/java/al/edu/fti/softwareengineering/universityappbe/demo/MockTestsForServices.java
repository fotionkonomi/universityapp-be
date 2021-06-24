package al.edu.fti.softwareengineering.universityappbe.demo;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.ContentEmptyException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseAnnouncmentService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation.UserServiceImpl;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.CourseAnnouncment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Post;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.FriendshipRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.UserRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CommentableAndLikeableRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CourseAnnouncmentRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CourseRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction.CommentRepository;
import io.jsonwebtoken.lang.Collections;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MockTestsForServices {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserService userService;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @MockBean
    private CourseAnnouncmentRepository courseAnnouncmentRepository;

    @Autowired
    private CourseAnnouncmentService courseAnnouncmentService;

    @Autowired
    private CommentableAndLikeableRepository commentableAndLikeableRepository;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Test
    void testFindByEmail() {
        User user = new User();
        user.setEmail("kfotjon@gmail.com");
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.of(user));

        UserDTO userDTO = this.userService.findByEmail(user.getEmail());

        verify(userRepository, times(1)).findByEmail(user.getEmail());

        assertNotNull(userDTO);
        assertTrue(userDTO.getEmail().equals(user.getEmail()));
        assertTrue(userDTO.getFirstName().equals(user.getFirstName()));
        assertTrue(userDTO.getLastName().equals(user.getLastName()));
        assertTrue(userDTO.getPassword().equals(user.getPassword()));
        assertTrue(userDTO.getProfilePicturePath().equals(user.getProfilePicturePath()));
    }

    @Test
    void testGetUsersEnrolledInACourse() {
        User user = new User();
        user.setEmail("kfotjon@gmail.com");
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");

        User user1 = new User();
        user1.setEmail("denis@gmail.com");
        user1.setFirstName("Denis");
        user1.setLastName("Koleci");
        user1.setPassword("test");
        user1.setProfilePicturePath("/test");

        User user2 = new User();
        user2.setEmail("kevin@gmail.com");
        user2.setFirstName("Kevin");
        user2.setLastName("Konomi");
        user2.setPassword("Shahollari");
        user2.setProfilePicturePath("/test");

        List<User> usersList = new ArrayList<>();
        usersList.add(user);
        usersList.add(user1);
        usersList.add(user2);

        when(userRepository.findAllByCoursesOfAUser_idAndIdIsNot(1L, 10L)).thenReturn(usersList);

        List<UserDTO> userDTOS = this.userService.getUsersEnrolledInACourse(1L, 10L);

        verify(userRepository, times(1)).findAllByCoursesOfAUser_idAndIdIsNot(1L, 10L);

        assertNotNull(userDTOS);
        assertTrue(userDTOS.size() == 3);
        assertTrue(userDTOS.get(0).getEmail().equals(user.getEmail()));

    }

    @Test
    void testAnnouncmentsOfACourse() {
        CourseAnnouncment courseAnnouncment = new CourseAnnouncment();
        courseAnnouncment.setContent("This is an announcement");
        List<CourseAnnouncment> courseAnnouncments = new ArrayList<>();
        courseAnnouncments.add(courseAnnouncment);
        when(courseAnnouncmentRepository.findAllByCourseField_Id(1L)).thenReturn(courseAnnouncments);

        List<CourseAnnouncmentDTO> courseAnnouncmentDTOS = this.courseAnnouncmentService.announcmentsOfACourse(1L);

        verify(courseAnnouncmentRepository, times(1)).findAllByCourseField_Id(1L);

        assertNotNull(courseAnnouncmentDTOS);
        assertTrue(courseAnnouncmentDTOS.size() == 1);
        assertTrue(courseAnnouncmentDTOS.get(0).getContent().equals(courseAnnouncment.getContent()));
    }

    @Test
    void testGetAllCommentsInACommentableAndLikeable() {
        Comment comment = new Comment();
        comment.setContent("This is a content");
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        when(commentRepository.findAllByCommentedContent_IdOrderByUpdatedAtDesc(1L)).thenReturn(comments);

        List<CommentDTO> commentDTOS = this.commentService.getAllCommentsInACommentableAndLikeable(1L);

        verify(commentRepository, times(1)).findAllByCommentedContent_IdOrderByUpdatedAtDesc(1L);

        assertNotNull(commentDTOS);
        assertTrue(comments.size() == 1);
        assertTrue(comments.get(0).getContent().equals(comment.getContent()));



    }

    @Test
    void testAddCommentToACommentableAndLikeableException() {
        Comment comment = new Comment();
        comment.setContent("");

        assertThrows(ContentEmptyException.class, () -> this.commentService.addCommentToACommentableAndLikeable(1L, "", 1L));
    }

    @Test
    void testFindAllByStudentEnrolled_idPageable() {
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setName("Course " + i);
            courses.add(course);
        }

        when(courseRepository.findAllByStudentsEnrolled_Id(1L, PageRequest.of(0, 10))).thenReturn(courses);

        List<CourseDTO> courseDTOS = this.courseService.findAllByStudentEnrolled_id(1L, 0);

        assertNotNull(courseDTOS);
        assertTrue(courseDTOS.size() == 10);
    }

    @Test
    void testFindAllByStudentEnrolled_id() {
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            Course course = new Course();
            course.setName("Course " + i);
            courses.add(course);
        }

        when(courseRepository.findAllByStudentsEnrolled_Id(1L)).thenReturn(courses);

        List<CourseDTO> courseDTOS = this.courseService.findAllByStudentEnrolled_id(1L);

        assertNotNull(courseDTOS);
        assertTrue(courseDTOS.size() == 12);
    }

    @Test
    void testFindAllCoursesAvailableForLoggedUser() {
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            Course course = new Course();
            course.setName("Course " + i);
            courses.add(course);
        }

        when(courseRepository.findAllAvailableCourses(1L, PageRequest.of(0, 10))).thenReturn(courses);

        List<CourseDTO> courseDTOS = this.courseService.findAllCoursesAvailableForLoggedUser(1L, 0);

        assertNotNull(courseDTOS);
        assertTrue(courseDTOS.size() == 12);
    }

}
