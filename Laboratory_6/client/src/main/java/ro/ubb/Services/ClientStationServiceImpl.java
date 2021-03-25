package ro.ubb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ro.ubb.Model.Station;

public class ClientStationServiceImpl extends ClientServices<Integer, Station> {
    @Override
    @Autowired
    protected void setEntityService(@Qualifier("station") EntityService<Integer, Station> entityService) {
        super.setEntityService(entityService);
    }
}
