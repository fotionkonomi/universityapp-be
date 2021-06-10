package al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.common.BaseDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EntityNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Collection;

public abstract class CommonCrudRestController<DTO extends BaseDTO, ID> {
    @Autowired
    protected BaseService<DTO, ID> service;

    @GetMapping("/page/{page}")
    public ResponseEntity<Collection<DTO>> findAll(@PathVariable("page") int pageNumber) {
        return ResponseEntity.ok(service.findAllPageable(pageNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findOne(@PathVariable("id") ID id) throws EntityNotFoundException {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createObject(@RequestBody DTO dto) throws URISyntaxException {
        dto = service.save(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateObject(@RequestBody DTO dto) throws URISyntaxException {
        dto = service.save(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteObject(@PathVariable("id") ID id) throws EntityNotFoundException {
        this.service.deleteById(id);
    }
}
