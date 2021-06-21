package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.LikeDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface LikeService extends BaseService<LikeDTO, Long> {
    void toggleLikeAComment(Long idComment, Long idUser);

    LikeDTO getLikeIfCommentIsAlreadyLiked(Long idComment, Long idUser);

    void toggleLikeContent(Long idCommentableAndLikeable, Long idUser);

    LikeDTO getLikeIfContentIsAlreadyLiked(Long idCommentableAndLikeable, Long idUser);

    List<LikeDTO> getLikesOfAComment(Long idComment);
}
