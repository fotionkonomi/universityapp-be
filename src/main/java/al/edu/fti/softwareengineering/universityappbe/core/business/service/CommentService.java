package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface CommentService extends BaseService<CommentDTO, Long> {

    void addCommentToACommentableAndLikeable(Long idCommentableAndLikeable, String content, Long idUser);

    List<CommentDTO> getAllCommentsInACommentableAndLikeable(Long idCommentableAndLikeable);
}
