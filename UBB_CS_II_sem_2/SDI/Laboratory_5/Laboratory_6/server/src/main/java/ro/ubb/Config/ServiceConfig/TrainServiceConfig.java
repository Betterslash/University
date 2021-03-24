package ro.ubb.Config.ServiceConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Services.TrainService;

@Configuration
public class TrainServiceConfig {
    @Bean
    public TrainService trainService(){
        return new TrainService();
    }
}
