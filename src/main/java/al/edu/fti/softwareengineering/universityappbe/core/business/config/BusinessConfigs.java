package al.edu.fti.softwareengineering.universityappbe.core.business.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfigs {

    /**
     * Store a ModelMapper bean in the Spring IOC Container
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
