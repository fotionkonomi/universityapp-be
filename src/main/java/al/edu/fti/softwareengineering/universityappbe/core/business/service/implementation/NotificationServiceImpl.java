package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.common.MessageConstants;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.NotificationDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.NotificationService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Notification;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl extends AbstractJpaService<NotificationDTO, Notification, Long> implements NotificationService {

    @Autowired
    private UserService userService;

    public NotificationServiceImpl() {
        super(Notification.class, NotificationDTO.class);
    }

    @Override
    public List<NotificationDTO> getAllUnseenNotificationsOfAUser(Long idUser) {
        return this.mapEntityListToDTO(getNotificationRepository().findAllByToUser_IdAndSeenIsFalse(idUser));
    }

    @Override
    public void sendNotificationForFriendRequest(Long idToUser, Long idFromUser) {
        UserDTO userDTOToUser = this.userService.findById(idToUser);
        UserDTO userDTOFromUser = this.userService.findById(idFromUser);
        NotificationDTO notification = new NotificationDTO();
        notification.setToUser(userDTOToUser);
        notification.setContent(MessageConstants.MSG_NOTIFICATION_FRIENDSHIP_REQUEST);
        notification.setParameters(userDTOFromUser.getFirstName() + " " + userDTOFromUser.getLastName());

        this.save(notification);
    }

    @Override
    public void sendNotificationForAcceptingFriendRequest(Long idToUser, Long idFromUser) {
        UserDTO userDTOToUser = this.userService.findById(idToUser);
        UserDTO userDTOFromUser = this.userService.findById(idFromUser);
        NotificationDTO notification = new NotificationDTO();
        notification.setToUser(userDTOToUser);
        notification.setContent(MessageConstants.MSG_NOTIFICATION_FRIENDSHIP_REQUEST_ACCEPT);
        notification.setParameters(userDTOFromUser.getFirstName() + " " + userDTOFromUser.getLastName());

        this.save(notification);
    }

    @Override
    public void sendNotificationForDecliningFriendRequest(Long idToUser, Long idFromUser) {
        UserDTO userDTOToUser = this.userService.findById(idToUser);
        UserDTO userDTOFromUser = this.userService.findById(idFromUser);
        NotificationDTO notification = new NotificationDTO();
        notification.setToUser(userDTOToUser);
        notification.setContent(MessageConstants.MSG_NOTIFICATION_FRIENDSHIP_REQUEST_DECLINED);
        notification.setParameters(userDTOFromUser.getFirstName() + " " + userDTOFromUser.getLastName());

        this.save(notification);
    }

    @Override
    public void seeNotification(Long idNotification) {
        NotificationDTO notificationDTO = this.findById(idNotification);
        notificationDTO.setSeen(true);
        this.save(notificationDTO);
    }


    private NotificationRepository getNotificationRepository() {
        return (NotificationRepository) repo;
    }
}