package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends AbstractJpaService<CommentDTO, Comment, Long> implements CommentService {
    public CommentServiceImpl() {
        super(Comment.class, CommentDTO.class);
    }
}
