package ro.ubb.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.EntityManagers.StationCreator;
import ro.ubb.EntityManagers.TimeTableCreator;
import ro.ubb.EntityManagers.TrainCreator;

@Configuration
public class EntityCreatorsConfig {
    @Bean
    public StationCreator stationCreator(){
        return new StationCreator();
    }
    @Bean
    public TrainCreator trainCreator(){
        return new TrainCreator();
    }
    @Bean
    public TimeTableCreator timeTableCreator(){
        return new TimeTableCreator();
    }
}
