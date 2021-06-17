package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.CommentableAndLikeableDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentableAndLikeableService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.CommentableAndLikeable;
import org.springframework.stereotype.Service;

@Service
public class CommentableAndLikeableServiceImpl extends AbstractJpaService<CommentableAndLikeableDTO, CommentableAndLikeable, Long> implements CommentableAndLikeableService {
    public CommentableAndLikeableServiceImpl() {
        super(CommentableAndLikeable.class, CommentableAndLikeableDTO.class);
    }
}
