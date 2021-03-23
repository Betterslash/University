package ro.ubb.Config.ServiceConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Services.TimeTableService;

@Configuration
public class TimeTableServiceConfig {
    @Bean
    public TimeTableService timeTableService(){
        return new TimeTableService();
    }
}
