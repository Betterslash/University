package ro.ubb.Config.CRUDUtilsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.CRUDUtils.TrainDBOService;

@Configuration
public class DBOTrainServiceConfig {
    @Bean
    public TrainDBOService trainDBOService(){
        return new TrainDBOService();
    }

}
