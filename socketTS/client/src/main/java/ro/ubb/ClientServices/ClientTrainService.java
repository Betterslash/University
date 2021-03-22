package ro.ubb.ClientServices;

import ro.ubb.ClientServices.ClientAbstraction.AbstractClientTransferService;
import ro.ubb.Model.Parsers.IParser;
import ro.ubb.Model.Train;
import ro.ubb.tcp.TcpClient;

public class ClientTrainService extends AbstractClientTransferService<Integer, Train> {
    public ClientTrainService(TcpClient tcpClient, String signature, IParser<Integer, Train> parser) {
        super(tcpClient, signature, parser);
    }
}
