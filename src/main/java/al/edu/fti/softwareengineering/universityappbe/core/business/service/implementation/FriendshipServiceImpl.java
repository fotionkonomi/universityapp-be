package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.FriendshipDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EntityNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.FriendshipAlreadyExists;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.FriendshipToYourselfException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.FriendshipService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.NotificationService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Friendship;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendshipServiceImpl extends AbstractJpaService<FriendshipDTO, Friendship, Long> implements FriendshipService {
    public FriendshipServiceImpl() {
        super(Friendship.class, FriendshipDTO.class);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void addFriendship(Long idLoggedUser, Long idRequestedTo) {
        if(checkIfFriendshipAlreadyExists(idLoggedUser, idRequestedTo)) {
            throw new FriendshipAlreadyExists();
        }
        if(idLoggedUser.equals(idRequestedTo)) {
            throw new FriendshipToYourselfException();
        }
        UserDTO loggedUserDTO = this.userService.findById(idLoggedUser);
        UserDTO requestedToUserDTO = this.userService.findById(idRequestedTo);

        FriendshipDTO friendshipDTO = FriendshipDTO.builder()
                .requestedBy(loggedUserDTO)
                .requestedTo(requestedToUserDTO)
                .active(false)
                .build();

        this.save(friendshipDTO);
        this.notificationService.sendNotificationForFriendRequest(idRequestedTo, idLoggedUser);
    }

    @Override
    public void acceptFriendRequest(Long idRequestedTo, Long idRequestedBy) {
        FriendshipDTO friendRequest = findFriendRequest(idRequestedTo, idRequestedBy);
        friendRequest.setActive(true);
        this.save(friendRequest);

        this.notificationService.sendNotificationForAcceptingFriendRequest(idRequestedBy, idRequestedTo);
    }

    @Override
    public void declineFriendRequest(Long idRequestedTo, Long idRequestedBy) {
        FriendshipDTO friendRequest = findFriendRequest(idRequestedTo, idRequestedBy);
        this.deleteById(friendRequest.getId());

        this.notificationService.sendNotificationForDecliningFriendRequest(idRequestedBy, idRequestedTo);
    }

    @Override
    public List<FriendshipDTO> getFriendshipRequestsForAUser(Long idRequestedTo) {
        return mapEntityListToDTO(this.getFriendshipRepository().findAllByRequestedTo_IdAndActiveIsFalse(idRequestedTo));
    }

    @Override
    public boolean checkIfFriendshipAlreadyExists(Long idLoggedUser, Long idPossibleFriend) {
        return this.getFriendshipRepository().findIfFriendshipExists(idLoggedUser, idPossibleFriend).isPresent();
    }

    @Override
    public FriendshipDTO getFriendshipIfAlreadyExists(Long idLoggedUser, Long idPossibleFriend) {
        Optional<Friendship> optionalFriendship = this.getFriendshipRepository().findIfFriendshipExists(idLoggedUser, idPossibleFriend);
        return optionalFriendship.map(this::mapFromEntity).orElse(null);
    }

    @Override
    public boolean checkIfFriendshipAlreadyExistsAndAccepted(Long idLoggedUser, Long idPossibleFriend) {
        return this.getFriendshipRepository().findIfFriendshipExistsAndAccepted(idLoggedUser, idPossibleFriend).isPresent();
    }

    @Override
    public List<FriendshipDTO> getFriendshipsOfAUser(Long idUser) {
        return mapEntityListToDTO(this.getFriendshipRepository().findAllByRequestedBy_IdOrRequestedTo_IdAndActiveIsTrue(idUser, idUser));
    }

    @Override
    public List<FriendshipDTO> getFriendshipsOfAUser(Long idUser, int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        List<Friendship> friendships = this.getFriendshipRepository().findAllByRequestedBy_IdOrRequestedTo_IdAndActiveIsTrue(idUser, idUser, PageRequest.of(pageNumber, 10));
        return mapEntityListToDTO(friendships);
    }

    private FriendshipDTO findFriendRequest(Long idRequestedTo, Long idRequestedBy) {
        return mapOptionalEntityToDTO(getFriendshipRepository().findFriendshipByRequestedBy_IdAndRequestedTo_IdAndActiveIsFalse(idRequestedBy, idRequestedTo)).orElseThrow(EntityNotFoundException::new);
    }

    private FriendshipRepository getFriendshipRepository() {
        return (FriendshipRepository) repo;
    }


}
