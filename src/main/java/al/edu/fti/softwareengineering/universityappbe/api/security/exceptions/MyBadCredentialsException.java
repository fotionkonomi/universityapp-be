package al.edu.fti.softwareengineering.universityappbe.api.security.exceptions;

import al.edu.fti.softwareengineering.universityappbe.api.security.constants.SecurityConstants;
import org.springframework.security.authentication.BadCredentialsException;

public class MyBadCredentialsException extends BadCredentialsException {
    public MyBadCredentialsException() {
        super(SecurityConstants.BAD_CREDENTIALS_EXCEPTION);
    }
}
