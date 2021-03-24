package ro.ubb.Config.ServerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.Services.EntityService;
import ro.ubb.Services.StationService;
import ro.ubb.Services.TimeTableService;
import ro.ubb.Services.TrainService;

@Configuration
public class ServerAppConfig {
    @Bean
    RmiServiceExporter rmiServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(EntityService.class);
        rmiServiceExporter.setService(new TrainService());
        rmiServiceExporter.setServiceName("TrainService");
        return rmiServiceExporter;
    }

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
