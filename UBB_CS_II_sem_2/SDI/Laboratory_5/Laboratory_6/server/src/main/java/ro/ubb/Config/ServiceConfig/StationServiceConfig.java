package ro.ubb.Config.ServiceConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Services.StationService;

@Configuration
public class StationServiceConfig {
    @Bean
    StationService entityService(){
        return new StationService();
    }
}
