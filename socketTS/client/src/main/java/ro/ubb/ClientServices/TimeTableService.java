package ro.ubb.ClientServices;

import ro.ubb.ClientServices.ClientAbstraction.IFeatureCaller;
import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Parsers.IParser;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;

public class TimeTableService extends IFeatureCaller<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {


    public TimeTableService(TcpClient tcpClient, String signature, IParser<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> parser) {
        super(tcpClient, signature, parser);
    }

    @Override
    public CompletableFuture<String> getTrainsPassingEveryStation() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, GET_TRAINS_PASSING_EVERY_STATION) , "");
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public CompletableFuture<String> getMostTraveledStation() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, GET_MOST_TRAVELED_STATION) , "");
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public CompletableFuture<String> getStationsPassedByEveryTrain() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, GET_STATIONS_PASSED_BY_EVERY_TRAIN) , "");
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }
}
