package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.FriendshipDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.FriendshipService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Friendship;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImpl extends AbstractJpaService<FriendshipDTO, Friendship, Long> implements FriendshipService {
    public FriendshipServiceImpl() {
        super(Friendship.class, FriendshipDTO.class);
    }
}
