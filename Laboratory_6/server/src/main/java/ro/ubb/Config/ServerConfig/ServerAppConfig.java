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
    RmiServiceExporter rmiTrainServiceExporter(TrainService trainService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(EntityService.class);
        rmiServiceExporter.setService(trainService);
        rmiServiceExporter.setServiceName("TrainService");
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter rmiStationServiceExporter(StationService stationService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(EntityService.class);
        rmiServiceExporter.setService(stationService);
        rmiServiceExporter.setServiceName("StationService");
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter rmiTimeTableServiceExporter(TimeTableService timeTableService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(EntityService.class);
        rmiServiceExporter.setService(timeTableService);
        rmiServiceExporter.setServiceName("TimeTableService");
        return rmiServiceExporter;
    }
}
