package al.edu.fti.softwareengineering.universityappbe.demo;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Notification;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.CourseAnnouncment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Post;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.enums.CourseRepeatType;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Like;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.NotificationRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.UserRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CourseAnnouncmentRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.CourseRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.PostRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction.CommentRepository;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction.LikeRepository;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UniversityAppBeApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CourseAnnouncmentRepository courseAnnouncmentRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindByEmail() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        userRepository.save(user);

        User userToTest = userRepository.findByEmail("kfotjon@gmail.com").get();

        assertNotNull(userToTest);
        assertEquals(userToTest.getEmail(), "kfotjon@gmail.com");
        assertEquals(userToTest.getFirstName(), "Fotion");
        assertEquals(userToTest.getLastName(), "Konomi");
        assertEquals(userToTest.getProfilePicturePath(), "/test");
        assertEquals(userToTest.getPassword(), "test");
    }

    @Test
    void testFindAllByCoursesOfAUser_idAndIdIsNot() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Denis");
        user1.setLastName("Koleci");
        user1.setPassword("test");
        user1.setProfilePicturePath("/test");
        user1.setEmail("denis@gmail.com");
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Kevin");
        user2.setLastName("Shahollari");
        user2.setPassword("test");
        user2.setProfilePicturePath("/test");
        user2.setEmail("kevin@gmail.com");
        user2 = userRepository.save(user2);

        Course course = new Course();
        course.setName("Inxhinieri software 2");
        course.setDescription("Test Description");
        course.setRepeatCount(1);
        course.setStartDateTime(new Date());
        course.setRepeatType(CourseRepeatType.DAILY);

        course = courseRepository.save(course);

        course.addUser(user);
        course = courseRepository.save(course);

        List<User> usersEnrolledInCourse = userRepository.findAllByCoursesOfAUser_idAndIdIsNot(course.getId(), user2.getId());

        assertTrue(usersEnrolledInCourse.contains(user));
        assertFalse(usersEnrolledInCourse.contains(user1));
        assertFalse(usersEnrolledInCourse.contains(user2));

    }

    @Test
    void testFindAllByStudentsEnrolled_Id() {
        Course course = new Course();
        course.setName("Inxhinieri software 2");
        course.setDescription("Test Description");
        course.setRepeatCount(1);
        course.setStartDateTime(new Date());
        course.setRepeatType(CourseRepeatType.DAILY);

        course = courseRepository.save(course);

        Course course1 = new Course();
        course1.setName("Databaze");
        course1.setDescription("Test Description");
        course1.setRepeatCount(1);
        course1.setStartDateTime(new Date());
        course1.setRepeatType(CourseRepeatType.DAILY);

        course1 = courseRepository.save(course1);

        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        course.addUser(user);
        course = courseRepository.save(course);

        List<Course> courses = courseRepository.findAllByStudentsEnrolled_Id(user.getId());

        assertTrue(courses.size() == 1);
        assertTrue(courses.get(0).getId() == course.getId());
        assertFalse(courses.contains(course1));


    }

    @Test
    void testFindAllByStudentsEnrolled_IdPageable() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        for(int i = 0; i < 12; i++) {
            Course course = new Course();
            course.setName("Inxhinieri software " + i);
            course.setDescription("Test Description");
            course.setRepeatCount(1);
            course.setStartDateTime(new Date());
            course.setRepeatType(CourseRepeatType.DAILY);

            courseRepository.save(course);
            course.addUser(user);
            courseRepository.save(course);

        }

        List<Course> courses = courseRepository.findAllByStudentsEnrolled_Id(user.getId(), PageRequest.of(0, 10));
        assertTrue(courses.size() == 10);


    }

    @Test
    void testFindAllAvailableCourses() {
        Course course = new Course();
        course.setName("Inxhinieri software 2");
        course.setDescription("Test Description");
        course.setRepeatCount(1);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(new Date());
        tomorrow.set(Calendar.DATE, tomorrow.get(Calendar.DATE) + 1);
        course.setStartDateTime(tomorrow.getTime());
        course.setRepeatType(CourseRepeatType.DAILY);

        course = courseRepository.save(course);

        Course course1 = new Course();
        course1.setName("Databaze");
        course1.setDescription("Test Description");
        course1.setRepeatCount(1);
        course1.setStartDateTime(new Date());
        course1.setRepeatType(CourseRepeatType.DAILY);

        course1 = courseRepository.save(course1);

        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        List<Course> availableCourses = courseRepository.findAllAvailableCourses(user.getId(), PageRequest.of(0, 10));
        assertTrue(availableCourses.size() > 0);
        assertTrue(availableCourses.get(0).getId() == course.getId());

    }

    @Test
    void testFindAllByPostedBy_Id() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        for(int i = 0; i < 12; i++) {
            Post post = new Post();
            post.setContent("This is post " + i);
            post.setPostedBy(user);

            postRepository.save(post);
        }

        List<Post> postsOfAUser = postRepository.findAllByPostedBy_Id(user.getId(), PageRequest.of(0, 10));

        assertTrue(postsOfAUser.size() == 10);
        assertTrue(postsOfAUser.get(0).getContent().equals("This is post 0"));
        assertTrue(postsOfAUser.get(0).getPostedBy().getId() == user.getId());
    }

    @Test
    void testFindAllByCommentedContent_IdOrderByUpdatedAtDesc() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        Post post = new Post();
        post.setContent("This is a post ");
        post.setPostedBy(user);

        postRepository.save(post);

        Comment comment = new Comment();
        comment.setContent("Comment");
        comment.setCommentedContent(post);

        commentRepository.save(comment);

        List<Comment> commentsToTest = commentRepository.findAllByCommentedContent_IdOrderByUpdatedAtDesc(post.getId());

        assertNotNull(commentsToTest);
        assertTrue(commentsToTest.size() == 1);
        assertTrue(commentsToTest.get(0).getContent().equals("Comment"));


    }

    @Test
    void testFindLikeByCommentLiked_IdAndInteractedBy_Id() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        Post post = new Post();
        post.setContent("This is a post ");
        post.setPostedBy(user);

        post = postRepository.save(post);

        Comment comment = new Comment();
        comment.setContent("Comment");
        comment.setCommentedContent(post);
        comment = commentRepository.save(comment);

        Like like = new Like();
        like.setCommentLiked(comment);
        like.setInteractedBy(user);
        like = likeRepository.save(like);

        Optional<Like> optionalLike = likeRepository.findLikeByCommentLiked_IdAndInteractedBy_Id(comment.getId(), user.getId());

        assertTrue(optionalLike.isPresent());

    }

    @Test
    void testFindLikeByContentLiked_IdAndInteractedBy_Id() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        Post post = new Post();
        post.setContent("This is a post ");
        post.setPostedBy(user);

        post = postRepository.save(post);

        Like like = new Like();
        like.setLikedContent(post);
        like.setInteractedBy(user);
        like = likeRepository.save(like);

        Optional<Like> optionalLike = likeRepository.findLikeByLikedContent_IdAndInteractedBy_Id(post.getId(), user.getId());

        assertTrue(optionalLike.isPresent());

    }

    @Test
    void testFindAllCommentLiked_id() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        Post post = new Post();
        post.setContent("This is a post ");
        post.setPostedBy(user);

        post = postRepository.save(post);

        Comment comment = new Comment();
        comment.setContent("Comment");
        comment.setCommentedContent(post);
        comment = commentRepository.save(comment);

        Like like = new Like();
        like.setCommentLiked(comment);
        like.setInteractedBy(user);
        like = likeRepository.save(like);

        List<Like> likes = likeRepository.findAllByCommentLiked_Id(comment.getId());

        assertNotNull(likes);
        assertTrue(likes.size() == 1);
        assertTrue(likes.get(0).getInteractedBy().getId() == user.getId());
        assertTrue(likes.get(0).getCommentLiked().getId() == comment.getId());

    }

    @Test
    void testFindAllContentLiked_id() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        Post post = new Post();
        post.setContent("This is a post ");
        post.setPostedBy(user);

        post = postRepository.save(post);

        Like like = new Like();
        like.setLikedContent(post);
        like.setInteractedBy(user);
        like = likeRepository.save(like);

        List<Like> likes = likeRepository.findAllByLikedContent_Id(post.getId());

        assertNotNull(likes);
        assertTrue(likes.size() == 1);
        assertTrue(likes.get(0).getInteractedBy().getId() == user.getId());
        assertTrue(likes.get(0).getLikedContent().getId() == post.getId());

    }

    @Test
    void testFindAllByCourseField_Id() {
        Course course = new Course();
        course.setName("Inxhinieri software 2");
        course.setDescription("Test Description");
        course.setRepeatCount(1);
        course.setStartDateTime(new Date());
        course.setRepeatType(CourseRepeatType.DAILY);

        course = courseRepository.save(course);

        CourseAnnouncment announcment = new CourseAnnouncment();
        announcment.setCourseField(course);
        announcment.setContent("test");
        announcment = courseAnnouncmentRepository.save(announcment);

        List<CourseAnnouncment> announcmentListToTest = this.courseAnnouncmentRepository.findAllByCourseField_Id(course.getId());

        assertNotNull(announcmentListToTest);
        assertTrue(announcmentListToTest.size() == 1);
        assertTrue(announcmentListToTest.get(0).getContent().equals(announcment.getContent()));
        assertTrue(announcmentListToTest.get(0).getCourseField().getId() == course.getId());
    }

    @Test
    void testFindAllByToUser_IdAndSeenIsFalseOrderByCreatedAtDesc() {
        User user = new User();
        user.setFirstName("Fotion");
        user.setLastName("Konomi");
        user.setPassword("test");
        user.setProfilePicturePath("/test");
        user.setEmail("kfotjon@gmail.com");
        user = userRepository.save(user);

        Notification notification = new Notification();
        notification.setContent("test");
        notification.setToUser(user);
        notificationRepository.save(notification);

        List<Notification> notifications = notificationRepository.findAllByToUser_IdAndSeenIsFalseOrderByCreatedAtDesc(user.getId());

        assertNotNull(notifications);
        assertTrue(notifications.size() == 1);
        assertTrue(notifications.get(0).getToUser().getId() == user.getId());
        assertTrue(notifications.get(0).getContent().equals(notification.getContent()));

    }

}
