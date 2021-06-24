package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.NotificationDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface NotificationService extends BaseService<NotificationDTO, Long> {
    /**
     * Returns all unseen notifications of a user
     * @param idUser
     * @return
     */
    List<NotificationDTO> getAllUnseenNotificationsOfAUser(Long idUser);

    /**
     * Sends a notification for a friend request
     * @param idToUser
     * @param idFromUser
     */
    void sendNotificationForFriendRequest(Long idToUser, Long idFromUser);

    /**
     * Sends a notification if a friend request has been accepted
     * @param idToUser
     * @param idFromUser
     */
    void sendNotificationForAcceptingFriendRequest(Long idToUser, Long idFromUser);

    /**
     * Sends a notification if a friend request has been declined
     * @param idToUser
     * @param idFromUser
     */
    void sendNotificationForDecliningFriendRequest(Long idToUser, Long idFromUser);

    /**
     * Makes a notification seen, so it will not be returned anymore
     * Similar to a soft delete
     * @param idNotification
     */
    void seeNotification(Long idNotification);

    /**
     * Sends notification if a user has made a post
     * @param userToNotify
     * @param poster
     */
    void sendNotificationForPost(UserDTO userToNotify, UserDTO poster);
}
