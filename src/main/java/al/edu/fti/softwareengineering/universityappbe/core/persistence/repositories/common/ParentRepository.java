package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ParentRepository<ENTITY extends BaseEntity, ID> extends JpaRepository<ENTITY, ID> {

}
