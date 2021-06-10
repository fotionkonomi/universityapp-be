package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.HallDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.HallService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Hall;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl extends AbstractJpaService<HallDTO, Hall, Long> implements HallService {
    public HallServiceImpl() {
        super(Hall.class, HallDTO.class);
    }
}
