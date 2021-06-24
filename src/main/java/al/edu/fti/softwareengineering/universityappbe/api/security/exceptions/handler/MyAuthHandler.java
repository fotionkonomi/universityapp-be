package al.edu.fti.softwareengineering.universityappbe.api.security.exceptions.handler;

import al.edu.fti.softwareengineering.universityappbe.api.rest.exceptionhandler.model.HttpErrorResponse;
import al.edu.fti.softwareengineering.universityappbe.api.rest.exceptionhandler.util.ExceptionMessageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom exception handler for authentications
 */
@Component
public class MyAuthHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExceptionMessageUtil messageUtil;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse();
        httpErrorResponse.setErrorCode(HttpServletResponse.SC_UNAUTHORIZED);
        httpErrorResponse.setPath(httpServletRequest.getRequestURI());
        httpErrorResponse.setMessage("Authorization Error: " + messageUtil.getLocalizedMessage(e.getMessage()));
        httpServletResponse.setStatus(httpErrorResponse.getErrorCode());
        httpErrorResponse.setLocalizedMessage(messageUtil.getLocalizedMessage(e.getMessage()));
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(httpServletResponse.getWriter(), httpErrorResponse);
    }
}
