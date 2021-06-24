package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Friendship;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository to make the interactions to the database that relate to the friendships
 */
@Repository
public interface FriendshipRepository extends ParentRepository<Friendship, Long> {
    /**
     * Finds if a friendship request has been sent but not yet accepted
     * @param idRequestedBy
     * @param idRequestedTo
     * @return
     */
    Optional<Friendship> findFriendshipByRequestedBy_IdAndRequestedTo_IdAndActiveIsFalse(Long idRequestedBy, Long idRequestedTo);

    /**
     * Finds all the friend requests sent to a user but not accepted yet
     * @param idRequestedTo
     * @return
     */
    List<Friendship> findAllByRequestedTo_IdAndActiveIsFalse(Long idRequestedTo);

    /**
     * Finds if a friendship between two users exist
     * This friendship can be not accepted yet
     * @param idUserLogged
     * @param idPossibleFriend
     * @return
     */
    @Query("SELECT f FROM Friendship f WHERE (f.requestedBy.id = :idUserLogged and f.requestedTo.id = :idPossibleFriend ) or (f.requestedBy.id = :idPossibleFriend and f.requestedTo.id = :idUserLogged)")
    Optional<Friendship> findIfFriendshipExists(Long idUserLogged, Long idPossibleFriend);

    /**
     * Finds if a friendship that has been accepted between two users exist
     * @param idUserLogged
     * @param idPossibleFriend
     * @return
     */
    @Query("SELECT f FROM Friendship f WHERE (f.requestedBy.id = :idUserLogged and f.requestedTo.id = :idPossibleFriend ) or (f.requestedBy.id = :idPossibleFriend and f.requestedTo.id = :idUserLogged) and f.active = true")
    Optional<Friendship> findIfFriendshipExistsAndAccepted(Long idUserLogged, Long idPossibleFriend);

    /**
     * Finds all the friendships of a user
     * @param userId
     * @param userIdAgain
     * @return
     */
    List<Friendship> findAllByRequestedBy_IdOrRequestedTo_IdAndActiveIsTrue(Long userId, Long userIdAgain);

    /**
     * Finds all the friendships of a user returned in pages
     * @param userId
     * @param userIdAgain
     * @param pageable
     * @return
     */
    List<Friendship> findAllByRequestedBy_IdOrRequestedTo_IdAndActiveIsTrue(Long userId, Long userIdAgain, Pageable pageable);
}
