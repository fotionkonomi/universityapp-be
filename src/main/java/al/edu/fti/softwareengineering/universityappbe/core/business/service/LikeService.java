package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.LikeDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface LikeService extends BaseService<LikeDTO, Long> {
    /**
     * Likes a comment if it was not liked before and vice versa
     * @param idComment
     * @param idUser
     */
    void toggleLikeAComment(Long idComment, Long idUser);

    /**
     * Returns the like object if a comment is already liked by a user
     * @param idComment
     * @param idUser
     * @return
     */
    LikeDTO getLikeIfCommentIsAlreadyLiked(Long idComment, Long idUser);

    /**
     * Likes a content if it was not liked before and vice versa
     * @param idCommentableAndLikeable
     * @param idUser
     */
    void toggleLikeContent(Long idCommentableAndLikeable, Long idUser);

    /**
     * Returns the like of a content if the content is already liked by the user
     * @param idCommentableAndLikeable
     * @param idUser
     * @return
     */
    LikeDTO getLikeIfContentIsAlreadyLiked(Long idCommentableAndLikeable, Long idUser);

    /**
     * Returns all the likes of a comment
     * @param idComment
     * @return
     */
    List<LikeDTO> getLikesOfAComment(Long idComment);

    /**
     * Returns all the likes of a content
     * @param idContent
     * @return
     */
    List<LikeDTO> getLikesOfAContent(Long idContent);
}
