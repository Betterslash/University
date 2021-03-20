package ro.ubb.ClientServices;

import ro.ubb.ClientServices.ClientAbstraction.AbstractClientTransferService;
import ro.ubb.Model.Station;
import ro.ubb.tcp.TcpClient;

public class ClientStationService extends AbstractClientTransferService<Integer, Station>{

    public ClientStationService(TcpClient tcpClient, String signature) {
        super(tcpClient, signature);
    }
}

