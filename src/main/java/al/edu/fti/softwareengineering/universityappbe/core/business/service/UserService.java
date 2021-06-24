package al.edu.fti.softwareengineering.universityappbe.core.business.service;

import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.exceptions.UserNotFoundException;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserDTO, Long> {

    /**
     * Finds a user by his/her email
     * @param email
     * @return
     * @throws UserNotFoundException
     */
    UserDTO findByEmail(final String email) throws UserNotFoundException;

    /**
     * Returns all the users enrolled in a course
     * @param idCourse
     * @param idLoggedUser
     * @return
     */
    List<UserDTO> getUsersEnrolledInACourse(Long idCourse, Long idLoggedUser);

    /**
     * Returns the friends of a user
     * @param idUser
     * @return
     */
    List<UserDTO> getFriendsOfAUser(Long idUser);

    /**
     * Returns the friends of a user in a pageable way
     * @param idUser
     * @param pageNumber
     * @return
     */
    List<UserDTO> getFriendsOfAUser(Long idUser, int pageNumber);
}
