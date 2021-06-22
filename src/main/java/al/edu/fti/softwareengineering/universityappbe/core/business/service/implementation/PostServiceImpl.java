package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.PostDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.ContentEmptyException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.NotificationService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.PostService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Post;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl extends AbstractJpaService<PostDTO, Post, Long> implements PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    public PostServiceImpl() {
        super(Post.class, PostDTO.class);
    }

    @Override
    public void addPostToTimeline(String content, Long idPostedBy) {
        if(!StringUtils.hasText(content)) {
            throw new ContentEmptyException();
        }
        UserDTO postedBy = this.userService.findById(idPostedBy);
        PostDTO postDTO = new PostDTO();
        postDTO.setContent(content);
        postDTO.setPostedBy(postedBy);

        this.save(postDTO);

        this.userService.getFriendsOfAUser(idPostedBy).forEach(userDTO -> this.notificationService.sendNotificationForPost(userDTO, postedBy));

    }

    @Override
    public void addPostFromComment(Long idPostedBy, CommentDTO commentDTO) {
        UserDTO postedBy = this.userService.findById(idPostedBy);
        PostDTO postDTO = new PostDTO();
        postDTO.setBornByComment(commentDTO);
        postDTO.setPostedBy(postedBy);
        postDTO.setContent(postedBy.getFirstName() + " " + postedBy.getLastName() + " comment: " + commentDTO.getContent());

        this.save(postDTO);

        this.userService.getFriendsOfAUser(idPostedBy).forEach(userDTO -> this.notificationService.sendNotificationForPost(userDTO, postedBy));


    }

    @Override
    public void addPostFromCourseEnrollment(Long idPostedBy, CourseDTO courseDTO) {
        UserDTO postedBy = this.userService.findById(idPostedBy);
        PostDTO postDTO = new PostDTO();
        postDTO.setBornByCourseEnrollment(courseDTO);
        postDTO.setPostedBy(postedBy);
        postDTO.setContent(postedBy.getFirstName() + " " + postedBy.getLastName() + " enrolled to: " + courseDTO.getName());

        this.save(postDTO);

        this.userService.getFriendsOfAUser(idPostedBy).forEach(userDTO -> this.notificationService.sendNotificationForPost(userDTO, postedBy));
    }

    @Override
    public List<PostDTO> getPostsOfAUser(Long idUser, int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        return mapEntityListToDTO(getPostRepository().findAllByPostedBy_Id(idUser, PageRequest.of(pageNumber, 10)));
    }

    @Override
    public List<PostDTO> getPostsToShowToLoggedUser(Long idLoggedUser, int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        List<UserDTO> friendsOfLoggedUser = this.userService.getFriendsOfAUser(idLoggedUser);
        List<Long> idsOfTheFriendsOfLoggedUser = new ArrayList<>();
        friendsOfLoggedUser.forEach(userDTO -> idsOfTheFriendsOfLoggedUser.add(userDTO.getId()));
        idsOfTheFriendsOfLoggedUser.add(idLoggedUser);
        return mapEntityListToDTO(getPostRepository().findAllByPostedBy_IdIsInOrderByCreatedAtDesc(idsOfTheFriendsOfLoggedUser, PageRequest.of(pageNumber, 10)));
    }

    private PostRepository getPostRepository() {
        return (PostRepository) repo;
    }


}
