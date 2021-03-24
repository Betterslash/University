package ro.ubb.Config.RepositoryConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.TimeTableRepository;

@Configuration
public class TimeTableRepositoryConfig {
    @Bean
    public TimeTableRepository timeTableRepository(){
        return new TimeTableRepository();
    }
}
