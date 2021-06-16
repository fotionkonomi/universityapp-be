package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.FriendshipDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface FriendshipService extends BaseService<FriendshipDTO, Long> {
    void addFriendship(Long idLoggedUser, Long idRequestedTo);

    void acceptFriendRequest(Long idRequestedBy, Long idRequestedTo);

    void declineFriendRequest(Long idRequestedBy, Long idRequestedTo);

    List<FriendshipDTO> getFriendshipRequestsForAUser(Long idRequestedTo);

    boolean checkIfFriendshipAlreadyExists(Long idLoggedUser, Long idPossibleFriend);

    boolean checkIfFriendshipAlreadyExistsAndAccepted(Long idLoggedUser, Long idPossibleFriend);
}
