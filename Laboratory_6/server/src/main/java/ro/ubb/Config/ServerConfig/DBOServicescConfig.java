package ro.ubb.Config.ServerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.CRUDUtils.StationDBOService;
import ro.ubb.Repository.CRUDUtils.TimeTableDBOService;
import ro.ubb.Repository.CRUDUtils.TrainDBOService;

@Configuration
public class DBOServicescConfig {
    @Bean
    public TrainDBOService trainDBOService(){
        return new TrainDBOService();
    }
    @Bean
    public StationDBOService stationDBOService(){
        return new StationDBOService();
    }
    @Bean
    public TimeTableDBOService timeTableDBOService(){
        return new TimeTableDBOService();
    }
}
