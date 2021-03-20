package ro.ubb.tcp;

import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Parsers.IParser;
import ro.ubb.TransferServices.ServerAbstractions.AbstractTransferServices;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

import static ro.ubb.tcp.TCPServer.getResponse;

public class CRUDInitializer<ID, E extends BaseEntity<ID>> {
    public void initialize(Map<String, UnaryOperator<Message>> unaryOperatorMap, AbstractTransferServices<ID, E> transferService, IParser<ID, E> parser){
        unaryOperatorMap.put(AbstractTransferServices.CREATE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.addEntity(parser.parse(request.getBody()));
                    return getResponse(res);
                });
        unaryOperatorMap.put(AbstractTransferServices.READ_ENTITIES + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.getEntities();
                    return getResponse(res);
                });
        unaryOperatorMap.put(AbstractTransferServices.UPDATE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.updateEntity(parser.parse(request.getBody()));
                    return getResponse(res);
                });
        unaryOperatorMap.put(AbstractTransferServices.DELETE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.deleteEntity(parser.parseID(request.getBody()));
                    return getResponse(res);
                });
    }
}
