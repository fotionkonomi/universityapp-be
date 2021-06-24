package al.edu.fti.softwareengineering.universityappbe.api.security;

import al.edu.fti.softwareengineering.universityappbe.api.security.constants.SecurityConstants;
import al.edu.fti.softwareengineering.universityappbe.api.security.exceptions.MyAuthorizationSchemaMissing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that will intercept all the calls to our web services to validate the jwt filter
 */
public class JwtRequestFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    private JwtUtil jwtUtil;

    private AuthenticationFailureHandler authFailureHandler;

    private RequestMatcher ignoreMatcher;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil, AuthenticationFailureHandler authFailureHandler, RequestMatcher ignoreMatcher) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.authFailureHandler = authFailureHandler;
        this.ignoreMatcher = ignoreMatcher;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZAION_HEADER);
            String username = null;
            String jwtToken = null;

            if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.SCHEMA)) {
                jwtToken = authorizationHeader.substring(7);
                UserDetails userDetailsExtractedByToken = jwtUtil.extractUserFromToken(jwtToken);
                username = userDetailsExtractedByToken.getUsername();

            } else {
                throw new MyAuthorizationSchemaMissing();
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);

        } catch(AuthenticationException e) {
            SecurityContextHolder.clearContext();
            authFailureHandler.onAuthenticationFailure(request, response, e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return ignoreMatcher.matches(request);
    }
}
