package ro.ubb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ro.ubb.Model.Train;

public class ClientTrainServiceImpl extends ClientServices<Integer, Train> {
    @Override
    @Autowired
    protected void setEntityService(@Qualifier("train") EntityService<Integer, Train> entityService) {
        super.setEntityService(entityService);
    }
}
