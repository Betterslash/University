package ro.ubb.Config.RepositoryConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.TrainRepository;

@Configuration
public class TrainRepositoryConfig {
    @Bean
    public TrainRepository repository(){
        return new TrainRepository();
    }
}
