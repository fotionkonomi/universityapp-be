package al.edu.fti.softwareengineering.universityappbe.api.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * If authentication was successful, the application will send a jwt token to the requester
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;
}
