package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Friendship;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendshipRepository extends ParentRepository<Friendship, Long> {
    Optional<Friendship> findFriendshipByRequestedBy_IdAndRequestedTo_IdAndActiveIsFalse(Long idRequestedBy, Long idRequestedTo);
}
