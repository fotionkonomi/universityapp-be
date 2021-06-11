package al.edu.fti.softwareengineering.universityappbe.core.business.service.implementation;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.EmailViolationException;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.UserNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.AbstractJpaService;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractJpaService<UserDTO, User, Long> implements UserService {
    public UserServiceImpl() {
        super(User.class, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) throws UserNotFoundException {
        UserDTO userDTO = findByEmailOrNull(email);
        if(userDTO != null) {
            return userDTO;
        }
        throw new UserNotFoundException();
    }

    @Override
    public List<UserDTO> getUsersEnrolledInACourse(Long idCourse) {
        return mapEntityListToDTO(getUserRepository().findAllByCoursesOfAUser_id(idCourse));
    }

    @Override
    public UserDTO save(UserDTO dto) {
        if(dto.getId() == null) {
            UserDTO existingUser = this.findByEmailOrNull(dto.getEmail());
            if(existingUser != null) {
                throw new EmailViolationException();
            }
        }
        return super.save(dto);
    }

    private UserDTO findByEmailOrNull(String email) {
        Optional<User> optionalUser = getUserRepository().findByEmail(email);
        if(optionalUser.isPresent()) {
            return mapFromEntity(optionalUser.get());
        } else {
            return null;
        }
    }

    private UserRepository getUserRepository() {
        return (UserRepository) repo;
    }
}
