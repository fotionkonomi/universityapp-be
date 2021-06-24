package al.edu.fti.softwareengineering.universityappbe.api.security.exceptions;

import al.edu.fti.softwareengineering.universityappbe.api.security.constants.SecurityConstants;
import org.springframework.security.core.AuthenticationException;

/**
 *  Thrown if the jwt token is missing
 */
public class MyAuthorizationSchemaMissing extends AuthenticationException {
    public MyAuthorizationSchemaMissing() {
        super(SecurityConstants.AUTHORIZATION_SCHEMA_MISSING);
    }
}
