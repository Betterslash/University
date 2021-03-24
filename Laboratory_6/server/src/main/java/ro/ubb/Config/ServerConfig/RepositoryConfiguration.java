package ro.ubb.Config.ServerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.StationRepository;
import ro.ubb.Repository.TimeTableRepository;
import ro.ubb.Repository.TrainRepository;

@Configuration
public class RepositoryConfiguration {
    @Bean
    public TrainRepository trainRepository(){
        return new TrainRepository();
    }

    @Bean
    public StationRepository stationRepository(){
        return new StationRepository();
    }

    @Bean
    public TimeTableRepository timeTableRepository(){
        return new TimeTableRepository();
    }
}
