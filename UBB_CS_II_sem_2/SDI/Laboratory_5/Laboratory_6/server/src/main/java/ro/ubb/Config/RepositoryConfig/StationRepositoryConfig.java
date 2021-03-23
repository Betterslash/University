package ro.ubb.Config.RepositoryConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.StationRepository;

@Configuration
public class StationRepositoryConfig {
    @Bean
    public StationRepository stationRepository(){
        return new StationRepository();
    }
}
