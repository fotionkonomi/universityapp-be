package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Notification;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to make the interactions to the database that relate to the notifications
 */
@Repository
public interface NotificationRepository extends ParentRepository<Notification, Long> {
    List<Notification> findAllByToUser_IdAndSeenIsFalseOrderByCreatedAtDesc(Long idUser);
}
