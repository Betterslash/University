package ro.ubb.Config.CRUDUtilsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.CRUDUtils.TimeTableDBOService;

@Configuration
public class DBOTimeTableServiceConfig {
    @Bean
    public TimeTableDBOService timeTableDBOService(){
        return new TimeTableDBOService();
    }
}
