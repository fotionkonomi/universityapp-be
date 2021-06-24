package al.edu.fti.softwareengineering.universityappbe.api.security.exceptions;

import al.edu.fti.softwareengineering.universityappbe.api.security.constants.SecurityConstants;
import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception thrown when the jwt token is not vali
 */
public class MyAuthenticationException extends AuthenticationException {
    public MyAuthenticationException() {
        super(SecurityConstants.TOKEN_INVALID);
    }
}
