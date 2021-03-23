package ro.ubb.Config.CRUDUtilsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.Repository.CRUDUtils.StationDBOService;

@Configuration
public class DBOStationServiceConfig {
    @Bean
    public StationDBOService stationDBOService(){
        return new StationDBOService();
    }
}
