package ro.ubb.tcp;

import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.BaseEntity;
import ro.ubb.TransferServices.ServerAbstractions.AbstractServerTransferServices;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

import static ro.ubb.tcp.TCPServer.getResponse;

public class CRUDInitializer<ID, E extends BaseEntity<ID>> {
    @SuppressWarnings("unchecked")
    public void initialize(Map<String, UnaryOperator<Message>> unaryOperatorMap, AbstractServerTransferServices<ID, E> transferService, E parser){
        unaryOperatorMap.put(AbstractServerTransferServices.CREATE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.addEntity((E) parser.parseEntity(request.getBody()));
                    return getResponse(res);
                });
        unaryOperatorMap.put(AbstractServerTransferServices.READ_ENTITIES + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.getEntities();
                    return getResponse(res);
                });
        unaryOperatorMap.put(AbstractServerTransferServices.UPDATE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.updateEntity((E) parser.parseEntity(request.getBody()));
                    return getResponse(res);
                });
        unaryOperatorMap.put(AbstractServerTransferServices.DELETE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.deleteEntity(parser.getId());
                    return getResponse(res);
                });
    }
}
