package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.FriendshipDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EntityNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.FriendshipService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Friendship;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImpl extends AbstractJpaService<FriendshipDTO, Friendship, Long> implements FriendshipService {
    public FriendshipServiceImpl() {
        super(Friendship.class, FriendshipDTO.class);
    }

    @Autowired
    private UserService userService;

    @Override
    public void addFriendship(Long idLoggedUser, Long idRequestedTo) {
        UserDTO loggedUserDTO = this.userService.findById(idLoggedUser);
        UserDTO requestedToUserDTO = this.userService.findById(idRequestedTo);

        FriendshipDTO friendshipDTO = FriendshipDTO.builder()
                .requestedBy(loggedUserDTO)
                .requestedTo(requestedToUserDTO)
                .active(false)
                .build();

        this.save(friendshipDTO);


    }

    @Override
    public void acceptFriendRequest(Long idRequestedTo, Long idRequestedBy) {
        FriendshipDTO friendRequest = findFriendRequest(idRequestedTo, idRequestedBy);
        friendRequest.setActive(true);
        this.save(friendRequest);
    }

    @Override
    public void declineFriendRequest(Long idRequestedTo, Long idRequestedBy) {
        FriendshipDTO friendRequest = findFriendRequest(idRequestedTo, idRequestedBy);
        this.deleteById(friendRequest.getId());
    }

    private FriendshipDTO findFriendRequest(Long idRequestedTo, Long idRequestedBy) {
        return mapOptionalEntityToDTO(getFriendshipRepository().findFriendshipByRequestedBy_IdAndRequestedTo_IdAndActiveIsFalse(idRequestedBy, idRequestedTo)).orElseThrow(EntityNotFoundException::new);
    }

    private FriendshipRepository getFriendshipRepository() {
        return (FriendshipRepository) repo;
    }
}
