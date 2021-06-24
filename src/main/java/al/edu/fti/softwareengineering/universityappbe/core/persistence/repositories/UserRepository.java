package al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.User;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ParentRepository<User, Long> {
    Optional<User> findByEmail(final String email);

    List<User> findAllByCoursesOfAUser_idAndIdIsNot(Long idCourse, Long idLoggedUser);
}
