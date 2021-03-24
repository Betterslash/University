package ro.ubb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;

public class ClientTimeTableServiceImpl extends ClientServices<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    @Override
    @Autowired
    protected void setEntityService(
            @Qualifier("timeTable")
                    EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> entityService
    ) {
        super.setEntityService(entityService);
    }
}
