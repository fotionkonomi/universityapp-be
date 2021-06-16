package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.CourseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.CommentableAndLikeableDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CourseService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends AbstractJpaService<CommentDTO, Comment, Long> implements CommentService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public CommentServiceImpl() {
        super(Comment.class, CommentDTO.class);
    }

    @Override
    public void addCommentToACourse(Long idCourse, String content, Long idUser) {
        CourseDTO courseDTO = this.courseService.findById(idCourse);
        UserDTO userDTO = this.userService.findById(idUser);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setInteractedBy(userDTO);
        commentDTO.setContent(content);
        commentDTO.setCommentedContent(courseDTO);

        this.save(commentDTO);
    }

    private CommentRepository getCommentRepository() {
        return (CommentRepository) repo;
    }


}
