package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.LikeDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

public interface LikeService extends BaseService<LikeDTO, Long> {
    void toggleLikeAComment(Long idComment, Long idUser);

    LikeDTO getLikeIfCommentIsAlreadyLiked(Long idComment, Long idUser);
}
