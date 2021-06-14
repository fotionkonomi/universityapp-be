package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.FriendshipDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

public interface FriendshipService extends BaseService<FriendshipDTO, Long> {
    void addFriendship(Long idLoggedUser, Long idRequestedTo);

    void acceptFriendRequest(Long idRequestedBy, Long idRequestedTo);

    void declineFriendRequest(Long idRequestedBy, Long idRequestedTo);
}
