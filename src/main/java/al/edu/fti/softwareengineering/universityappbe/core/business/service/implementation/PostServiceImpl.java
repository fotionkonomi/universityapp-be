package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.PostDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.PostService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Post;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends AbstractJpaService<PostDTO, Post, Long> implements PostService {
    public PostServiceImpl() {
        super(Post.class, PostDTO.class);
    }
}
