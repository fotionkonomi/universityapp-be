package al.edu.fti.softwareengineering.universityappbe.api.security.constants;

public class SecurityConstants {
    public static final String SCHEMA = "Bearer";
    public static final String AUTHORIZAION_HEADER = "Authorization";
    public static final String AUTHENTICATION_ENDPOINT = "/authentication";
    public static final String USER_REGISTRATION_ENDPOINT = "/user";

    // Exception message keys
    public static final String BAD_CREDENTIALS_EXCEPTION = "login.wrong.password";
    public static final String TOKEN_INVALID = "token.invalid";
    public static final String AUTHORIZATION_SCHEMA_MISSING = "authorization.schema.missing";
}
