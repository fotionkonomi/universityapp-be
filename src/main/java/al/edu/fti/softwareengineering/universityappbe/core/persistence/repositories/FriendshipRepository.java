package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Friendship;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends ParentRepository<Friendship, Long> {
    Optional<Friendship> findFriendshipByRequestedBy_IdAndRequestedTo_IdAndActiveIsFalse(Long idRequestedBy, Long idRequestedTo);

    List<Friendship> findAllByRequestedTo_IdAndActiveIsFalse(Long idRequestedTo);

    @Query("SELECT f FROM Friendship f WHERE (f.requestedBy.id = :idUserLogged and f.requestedTo.id = :idPossibleFriend ) or (f.requestedBy.id = :idPossibleFriend and f.requestedTo.id = :idUserLogged)")
    Optional<Friendship> findIfFriendshipExists(Long idUserLogged, Long idPossibleFriend);

    @Query("SELECT f FROM Friendship f WHERE (f.requestedBy.id = :idUserLogged and f.requestedTo.id = :idPossibleFriend ) or (f.requestedBy.id = :idPossibleFriend and f.requestedTo.id = :idUserLogged) and f.active = true")
    Optional<Friendship> findIfFriendshipExistsAndAccepted(Long idUserLogged, Long idPossibleFriend);

    List<Friendship> findAllByRequestedBy_IdOrRequestedTo_IdAndActiveIsTrue(Long userId, Long userIdAgain);

    List<Friendship> findAllByRequestedBy_IdOrRequestedTo_IdAndActiveIsTrue(Long userId, Long userIdAgain, Pageable pageable);
}
