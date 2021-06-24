package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.Hall;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to make the interactions to the database that relate to the halls
 */
@Repository
public interface HallRepository extends ParentRepository<Hall, Long> {
}
