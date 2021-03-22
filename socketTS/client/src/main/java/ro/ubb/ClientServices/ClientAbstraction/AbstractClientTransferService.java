package ro.ubb.ClientServices.ClientAbstraction;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Parsers.IParser;
import ro.ubb.TransferServices.ServerAbstractions.AbstractTransferServices;
import ro.ubb.tcp.TcpClient;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public abstract class AbstractClientTransferService<ID, E extends BaseEntity<ID>> extends AbstractTransferServices<ID, E> {

    protected final TcpClient tcpClient;
    protected final IParser<ID, E> parser;

    public AbstractClientTransferService(TcpClient tcpClient, String signature, IParser<ID, E> parser) {
        super(signature);
        this.tcpClient = tcpClient;
        this.parser = parser;
    }

    /**
     *
     * @returns all objects of type Station
     */
    @Override
    public CompletableFuture<Set<E>> getEntities() {
        return CompletableFuture.supplyAsync(() ->{
            Message request = new Message(new Header(StatusCodes.OK, AbstractTransferServices.READ_ENTITIES+this.getSS()), "");
            Message response = this.tcpClient.sendAndReceive(request);
            return Arrays.stream(response.getBody().split("\n"))
                    .map(this.parser::parse)
                    .collect(Collectors.toSet());
        });
    }

    /**
     * adds an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> addEntity(E entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, AbstractTransferServices.CREATE_ENTITY+this.getSS()), entity.csvFileFormat());
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * deletes an entity
     * @param integer
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> deleteEntity(ID integer) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, AbstractTransferServices.DELETE_ENTITY+this.getSS()), integer.toString() + LINE_SEPARATOR);
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * updates an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> updateEntity(E entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, AbstractTransferServices.UPDATE_ENTITY +this.getSS()), entity.csvFileFormat());
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }
}
