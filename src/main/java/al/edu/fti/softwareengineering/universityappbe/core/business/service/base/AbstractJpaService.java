package al.edu.fti.softwareengineering.universityappbe.core.business.service.base;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.BaseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EntityNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.BaseEntity;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
public abstract class AbstractJpaService<DTO extends BaseDTO, ENTITY extends BaseEntity, ID> implements BaseService<DTO, ID> {

    private Class<ENTITY> classOfEntity;
    private Class<DTO> classOfDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    protected ParentRepository<ENTITY, ID> repo;

    public AbstractJpaService(Class<ENTITY> classOfEntity, Class<DTO> classOfDTO) {
        this.classOfEntity = classOfEntity;
        this.classOfDTO = classOfDTO;
    }

    @Override
    public DTO findById(ID id) throws EntityNotFoundException {
        Optional<ENTITY> optionalEntity = repo.findById(id);

        Optional<DTO> optionalDTO = mapOptionalEntityToDTO(optionalEntity);
        return optionalDTO.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public DTO save(DTO dto) {
        ENTITY mappedEntity = mapFromDTO(dto);
        mappedEntity = repo.save(mappedEntity);
        log.info(classOfEntity.getName() + " object saved successfully: " + mappedEntity);
        return mapFromEntity(mappedEntity);
    }

    @Override
    public void deleteById(ID id) {
        this.repo.deleteById(id);
    }

    @Override
    public List<DTO> findAllPageable(int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        Page<ENTITY> entityList = repo.findAll(PageRequest.of(pageNumber, 10));
        List<DTO> dtoList = mapEntityListToDTO(entityList.getContent());
        return dtoList;
    }

    protected ENTITY mapFromDTO(DTO dto) {
        return modelMapper.map(dto, classOfEntity);
    }

    protected DTO mapFromEntity(ENTITY entity) {
        return modelMapper.map(entity, classOfDTO);
    }

    protected Optional<DTO> mapOptionalEntityToDTO(Optional<ENTITY> optionalEntity) {
        return Optional.ofNullable(optionalEntity.isPresent() ? mapFromEntity(optionalEntity.get()) : null);
    }

    protected List<DTO> mapEntityListToDTO(Collection<ENTITY> entityList) {
        List<DTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(mapFromEntity(entity)));
        return dtoList;
    }
}
