package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Like;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository to make the interactions to the database that relate to the likes
 */
@Repository
public interface LikeRepository extends ParentRepository<Like, Long> {

    /**
     * Finds if the comment that is passed in the parameter is liked by the user passed in the other parameter
     * @param idComment
     * @param idUser
     * @return
     */
    Optional<Like> findLikeByCommentLiked_IdAndInteractedBy_Id(Long idComment, Long idUser);

    /**
     * Finds if the content that is passed in the parameter is liked by the user passed in the other parameter
     * @param idCommentableAndLikeable
     * @param idUser
     * @return
     */
    Optional<Like> findLikeByLikedContent_IdAndInteractedBy_Id(Long idCommentableAndLikeable, Long idUser);

    /**
     * Finds all the likes of a comment
     * @param idCommentLiked
     * @return
     */
    List<Like> findAllByCommentLiked_Id(Long idCommentLiked);

    /**
     * Finds all the likes of a content
     * @param idContentLiked
     * @return
     */
    List<Like> findAllByLikedContent_Id(Long idContentLiked);

}
