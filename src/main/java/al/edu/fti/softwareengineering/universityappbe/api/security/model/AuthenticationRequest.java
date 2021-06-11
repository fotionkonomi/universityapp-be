package al.edu.fti.softwareengineering.universityappbe.api.security.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
