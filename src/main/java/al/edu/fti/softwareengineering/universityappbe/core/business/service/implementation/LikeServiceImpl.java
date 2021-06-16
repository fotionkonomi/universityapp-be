package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.LikeDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.LikeService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Like;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl extends AbstractJpaService<LikeDTO, Like, Long> implements LikeService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    public LikeServiceImpl() {
        super(Like.class, LikeDTO.class);
    }

    @Override
    public void toggleLikeAComment(Long idComment, Long idUser) {
        LikeDTO likeDTO = this.getLikeIfCommentIsAlreadyLiked(idComment, idUser);
        if (likeDTO != null) {
            this.deleteById(likeDTO.getId());
        } else {
            CommentDTO commentDTO = this.commentService.findById(idComment);
            UserDTO userLogged = this.userService.findById(idUser);
            likeDTO = new LikeDTO();
            likeDTO.setCommentLiked(commentDTO);
            likeDTO.setInteractedBy(userLogged);
            this.save(likeDTO);
        }
    }

    @Override
    public LikeDTO getLikeIfCommentIsAlreadyLiked(Long idComment, Long idUser) {
        Optional<Like> optionalLike = getLikeRepository().findLikeByCommentLiked_IdAndInteractedBy_Id(idComment, idUser);
        return optionalLike.map(this::mapFromEntity).orElse(null);
    }

    private LikeRepository getLikeRepository() {
        return (LikeRepository) repo;
    }
}
