package ro.ubb.TransferServices.ServerAbstractions;

import ro.ubb.Model.BaseEntity;
import ro.ubb.TransferServices.ITransferService;

public abstract class AbstractServerTransferServices<ID, E extends BaseEntity<ID>> implements ITransferService<ID, E> {
    public String SERVICE_SIGNATURE;
    public static final String STATION_SIGNATURE = "Station";
    public static final String TRAIN_SIGNATURE = "Train";
    public static final String TIME_TABLE_SIGNATURE = "TimeTable";
    protected AbstractServerTransferServices(String service_signature) {
        SERVICE_SIGNATURE = service_signature;
    }
    public String getSS(){
        return this.SERVICE_SIGNATURE;
    }
}
