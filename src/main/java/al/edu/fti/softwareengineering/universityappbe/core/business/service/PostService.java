package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.PostDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface PostService extends BaseService<PostDTO, Long> {
    void addPostToTimeline(String content, Long idPostedBy);

    void addPostFromComment(Long idPostedBy, CommentDTO commentDTO);

    void addPostFromCourseEnrollment(Long idPostedBy, CourseDTO courseDTO);

    List<PostDTO> getPostsOfAUser(Long idUser, int pageNumber);

    List<PostDTO> getPostsToShowToLoggedUser(Long idLoggedUser, int pageNumber);
}
