package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.UserDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/user")
public class UserController extends CommonCrudRestController<UserDTO, Long> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Void> createObject(@RequestBody UserDTO dto) throws URISyntaxException {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return super.createObject(dto);
    }

    @PostMapping("/email")
    public UserDTO findUserByEmail(@RequestBody String email) {
        return ((UserService) service).findByEmail(email);
    }

}
