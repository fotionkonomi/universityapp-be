package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.LikeDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.LikeService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.userInteractions.Like;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl extends AbstractJpaService<LikeDTO, Like, Long> implements LikeService {
    public LikeServiceImpl() {
        super(Like.class, LikeDTO.class);
    }
}
