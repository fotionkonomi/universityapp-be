package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.NotificationDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface NotificationService extends BaseService<NotificationDTO, Long> {
    List<NotificationDTO> getAllUnseenNotificationsOfAUser(Long idUser);

    void sendNotificationForFriendRequest(Long idToUser, Long idFromUser);

    void sendNotificationForAcceptingFriendRequest(Long idToUser, Long idFromUser);

    void sendNotificationForDecliningFriendRequest(Long idToUser, Long idFromUser);

    void seeNotification(Long idNotification);

    void sendNotificationForPost(UserDTO userToNotify, UserDTO poster);
}
