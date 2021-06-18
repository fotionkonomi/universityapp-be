package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.commentableAndLikeable.Post;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends ParentRepository<Post, Long> {
    List<Post> findAllByPostedBy_Id(Long idUser, Pageable pageable);
}
