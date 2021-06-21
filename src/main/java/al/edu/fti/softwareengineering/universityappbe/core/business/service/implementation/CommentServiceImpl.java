package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.CommentableAndLikeableDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.ContentEmptyException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.*;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CommentServiceImpl extends AbstractJpaService<CommentDTO, Comment, Long> implements CommentService {

    @Autowired
    private CommentableAndLikeableService commentableAndLikeableService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public CommentServiceImpl() {
        super(Comment.class, CommentDTO.class);
    }

    @Override
    public void addCommentToACommentableAndLikeable(Long idCommentableAndLikeable, String content, Long idUser) {
        if(!StringUtils.hasText(content)) {
            throw new ContentEmptyException();
        }
        CommentableAndLikeableDTO commentableAndLikeableDTO = this.commentableAndLikeableService.findById(idCommentableAndLikeable);
        UserDTO userDTO = this.userService.findById(idUser);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setInteractedBy(userDTO);
        commentDTO.setContent(content);
        commentDTO.setCommentedContent(commentableAndLikeableDTO);

        commentDTO = this.save(commentDTO);
        this.postService.addPostFromComment(userDTO.getId(), commentDTO);
    }

    @Override
    public List<CommentDTO> getAllCommentsInACommentableAndLikeable(Long idCommentableAndLikeable) {
        return mapEntityListToDTO(getCommentRepository().findAllByCommentedContent_IdOrderByUpdatedAtDesc(idCommentableAndLikeable));
    }

    private CommentRepository getCommentRepository() {
        return (CommentRepository) repo;
    }


}
