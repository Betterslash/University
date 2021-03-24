package ro.ubb.Config.ClientConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Services.ClientStationServiceImpl;
import ro.ubb.Services.ClientTimeTableServiceImpl;
import ro.ubb.Services.ClientTrainServiceImpl;
import ro.ubb.Services.EntityService;
import ro.ubb.Ui.ClientConsole;

@Configuration
public class ClientAppConfig {
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/TrainService");
        rmiProxyFactoryBean.setServiceInterface(EntityService.class);
        return rmiProxyFactoryBean;
    }
    @Bean
    public EntityService<Integer, Train> trainService(){
        return new ClientTrainServiceImpl();
    }
    @Bean
    public EntityService<Integer, Station> stationService(){
        return new ClientStationServiceImpl();
    }
    @Bean
    public EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService(){
        return new ClientTimeTableServiceImpl();
    }
    @Bean
    public ClientConsole clientConsole(){
        return new ClientConsole(trainService(),stationService(), timeTableService());
    }
}
