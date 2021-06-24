package al.edu.fti.softwareengineering.universityappbe.api;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApiConfig {

    /**
     * Creates a bean in the Spring IOC Container that is used for password encoding
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    /**
     * Creates a bean in the Spring IOC Container that reads from properties files
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.addBasenames("exception_messages");
        return resource;
    }
}
