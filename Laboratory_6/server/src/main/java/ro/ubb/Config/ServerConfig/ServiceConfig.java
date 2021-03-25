package ro.ubb.Config.ServerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Services.StationService;
import ro.ubb.Services.TimeTableService;
import ro.ubb.Services.TrainService;

@Configuration
public class ServiceConfig {
    @Bean
    public StationService entityService(){
        return new StationService();
    }
    @Bean
    public TimeTableService timeTableService(){
        return new TimeTableService();
    }
    @Bean
    public TrainService trainService(){
        return new TrainService();
    }
}
