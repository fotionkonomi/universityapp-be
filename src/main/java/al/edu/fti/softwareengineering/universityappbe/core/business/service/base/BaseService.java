package al.edu.fti.softwareengineering.universityappbe.core.business.service.base;

import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EntityNotFoundException;

import java.awt.print.Pageable;
import java.util.List;

public interface BaseService<DTO, ID> {
    /**
     * Finds an entity based on its id and converts it into a dto
     * @param id
     * @return
     * @throws EntityNotFoundException
     */
    DTO findById(ID id) throws EntityNotFoundException;

    /**
     * Saves a dto by converting it to a entity and then return the saved entity in a dto form
     * @param dto
     * @return
     */
    DTO save(DTO dto);

    /**
     * Deletes a row in the database based on its id
     * Objects that extend SoftDeletionEntity will only be soft deleted
     * @param id
     */
    void deleteById(ID id);

    /**
     * Finds all data an returns them in a pageable way
     * @param pageNumber
     * @return
     */
    List<DTO> findAllPageable(int pageNumber);
}
