package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.UserNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserDTO, Long> {
    UserDTO findByEmail(final String email) throws UserNotFoundException;

    List<UserDTO> getUsersEnrolledInACourse(Long idCourse);
}
