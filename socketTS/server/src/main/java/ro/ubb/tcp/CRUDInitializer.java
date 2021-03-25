package ro.ubb.tcp;

import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Parsers.IParser;
import ro.ubb.TransferServices.ServerAbstractions.AbstractTransferServices;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

import static ro.ubb.tcp.TCPServer.executor;
import static ro.ubb.tcp.TCPServer.getResponse;

public class CRUDInitializer<ID, E extends BaseEntity<ID>> {
    public void initialize(Map<String, UnaryOperator<Message>> unaryOperatorMap, AbstractTransferServices<ID, E> transferService, IParser<ID, E> parser){
        unaryOperatorMap.put(AbstractTransferServices.CREATE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.addEntity(parser.parse(request.getBody()));
                    return CompletableFuture.supplyAsync(() -> getResponse(res), executor).join();
                });
        unaryOperatorMap.put(AbstractTransferServices.READ_ENTITIES + transferService.getSS(),
                request -> {
                    CompletableFuture<Set<E>> res = transferService.getEntities();
                    return CompletableFuture.supplyAsync(() -> getResponse(
                            CompletableFuture.supplyAsync(() ->res.join().
                                stream()
                            .map(BaseEntity::csvFileFormat)
                            .reduce((acc,  e) -> acc + e)
                                    .orElseThrow(() -> new ExceptionInInitializerError("Someyhing went wrong furing parsing !"))
                            )
                            ), executor).join();
                });
        unaryOperatorMap.put(AbstractTransferServices.UPDATE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.updateEntity(parser.parse(request.getBody()));
                    return CompletableFuture.supplyAsync(() -> getResponse(res), executor).join();
                });
        unaryOperatorMap.put(AbstractTransferServices.DELETE_ENTITY + transferService.getSS(),
                request -> {
                    CompletableFuture<String> res = transferService.deleteEntity(parser.parseID(request.getBody()));
                    return CompletableFuture.supplyAsync(() -> getResponse(res), executor).join();
                });
    }
}