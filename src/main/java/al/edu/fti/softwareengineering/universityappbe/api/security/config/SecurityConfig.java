package al.edu.fti.softwareengineering.universityappbe.api.security.config;

import al.edu.fti.softwareengineering.universityappbe.api.security.JwtRequestFilter;
import al.edu.fti.softwareengineering.universityappbe.api.security.JwtUtil;
import al.edu.fti.softwareengineering.universityappbe.api.security.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationFailureHandler authFailureHandler;

    private static final AntPathRequestMatcher[] defaultPermittedMatchers = new AntPathRequestMatcher[]
            {
                    new AntPathRequestMatcher(SecurityConstants.AUTHENTICATION_ENDPOINT, HttpMethod.POST.toString()),
                    new AntPathRequestMatcher(SecurityConstants.USER_REGISTRATION_ENDPOINT, HttpMethod.POST.toString())
            };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(defaultPermittedMatchers)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(this.getJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private JwtRequestFilter getJwtRequestFilter() {
        RequestMatcher ignoreMatcher = new OrRequestMatcher(this.defaultPermittedMatchers);
        return new JwtRequestFilter(userDetailsService, jwtUtil, authFailureHandler, ignoreMatcher);
    }
}
