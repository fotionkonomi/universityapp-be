package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Like;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends ParentRepository<Like, Long> {
    Optional<Like> findLikeByCommentLiked_IdAndInteractedBy_Id(Long idComment, Long idUser);

    Optional<Like> findLikeByLikedContent_IdAndInteractedBy_Id(Long idCommentableAndLikeable, Long idUser);

    List<Like> findAllByCommentLiked_Id(Long idCommentLiked);

}
