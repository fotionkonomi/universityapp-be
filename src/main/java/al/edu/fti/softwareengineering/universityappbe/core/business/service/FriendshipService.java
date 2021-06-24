package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.FriendshipDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface FriendshipService extends BaseService<FriendshipDTO, Long> {
    /**
     * Sends a friend request from the logged user to the requested user
     * @param idLoggedUser
     * @param idRequestedTo
     */
    void addFriendship(Long idLoggedUser, Long idRequestedTo);

    /**
     * Accepts a friend request
     * @param idRequestedBy
     * @param idRequestedTo
     */
    void acceptFriendRequest(Long idRequestedBy, Long idRequestedTo);

    /**
     * Declines a friend request
     * @param idRequestedBy
     * @param idRequestedTo
     */
    void declineFriendRequest(Long idRequestedBy, Long idRequestedTo);

    /**
     * Returns all friend requests sent to a user
     * @param idRequestedTo
     * @return
     */
    List<FriendshipDTO> getFriendshipRequestsForAUser(Long idRequestedTo);

    /**
     * Checks if a friendship already exists
     * @param idLoggedUser
     * @param idPossibleFriend
     * @return
     */
    boolean checkIfFriendshipAlreadyExists(Long idLoggedUser, Long idPossibleFriend);

    /**
     * Returns the friendship if it already exists
     * @param idLoggedUser
     * @param idPossibleFriend
     * @return
     */
    FriendshipDTO getFriendshipIfAlreadyExists(Long idLoggedUser, Long idPossibleFriend);

    /**
     * Finds if a friend request exists between the two users and it has been accepted
     * @param idLoggedUser
     * @param idPossibleFriend
     * @return
     */
    boolean checkIfFriendshipAlreadyExistsAndAccepted(Long idLoggedUser, Long idPossibleFriend);

    /**
     * Returns the friendships of a user
     * @param idUser
     * @return
     */
    List<FriendshipDTO> getFriendshipsOfAUser(Long idUser);

    /**
     * Returns the friendships of a user in a pageable way
     * @param idUser
     * @param pageNumber
     * @return
     */
    List<FriendshipDTO> getFriendshipsOfAUser(Long idUser, int pageNumber);
}
