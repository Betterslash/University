package ro.ubb.tcp;

import ro.ubb.Model.Exceptions.MessageException;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.TransferServices.ITransferService;

import java.io.IOException;
import java.net.Socket;

public class TcpClient {

    public TcpClient() {
    }

    /**
     * sends a message through a socket and gets back a response
     * @param request
     * @returns the received message from the server
     */
    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(ITransferService.HOST, ITransferService.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()) {

            System.out.println("sending request: " + request);
            request.writeTo(os);
            System.out.println("request sent");
            Message response = new Message();
            response.readFrom(is);
            System.out.println("got response ");
            return response;

        } catch (IOException e) {
            e.printStackTrace();
            throw new MessageException("exception in send and receive");
        }

    }
}

