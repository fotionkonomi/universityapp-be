package al.edu.fti.softwareengineering.universityappbe.core.business.service.base;

import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EntityNotFoundException;

import java.awt.print.Pageable;
import java.util.List;

public interface BaseService<DTO, ID> {
    DTO findById(ID id) throws EntityNotFoundException;

    DTO save(DTO dto);

    List<DTO> findAll();

    void deleteById(ID id);

    List<DTO> findAllPageable(int pageNumber);
}
