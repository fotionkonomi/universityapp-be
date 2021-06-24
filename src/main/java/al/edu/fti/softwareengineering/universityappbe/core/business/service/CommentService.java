package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface CommentService extends BaseService<CommentDTO, Long> {

    /**
     * Adds a comment to a specific content
     * @param idCommentableAndLikeable
     * @param content
     * @param idUser
     */
    void addCommentToACommentableAndLikeable(Long idCommentableAndLikeable, String content, Long idUser);

    /**
     * Gets all comments in a specific content
     * @param idCommentableAndLikeable
     * @return
     */
    List<CommentDTO> getAllCommentsInACommentableAndLikeable(Long idCommentableAndLikeable);
}
