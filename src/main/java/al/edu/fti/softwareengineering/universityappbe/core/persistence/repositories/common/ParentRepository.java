package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * A generic base repository that all the other repositories will extend from, to reduce dublicated codes
 * @param <ENTITY>
 * @param <ID>
 */
@NoRepositoryBean
public interface ParentRepository<ENTITY extends BaseEntity, ID> extends JpaRepository<ENTITY, ID> {
}
