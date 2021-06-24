package al.edu.fti.softwareengineering.universityappbe.api.security.model;

import lombok.Data;

@Data
/**
 * Class that will be populated by the data that the user will enter in the login page
 */
public class AuthenticationRequest {
    private String username;
    private String password;
}
