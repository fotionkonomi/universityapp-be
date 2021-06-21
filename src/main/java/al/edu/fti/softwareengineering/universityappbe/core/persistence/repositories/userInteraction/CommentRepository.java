package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.userInteraction;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends ParentRepository<Comment, Long> {

    List<Comment> findAllByCommentedContent_IdOrderByUpdatedAtDesc(Long idContent);
}
