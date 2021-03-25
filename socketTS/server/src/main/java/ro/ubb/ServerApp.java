package ro.ubb;

import ro.ubb.TransferServices.ITransferService;
import ro.ubb.tcp.TCPServer;

public class ServerApp {
    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer( ITransferService.PORT);
        tcpServer.initializeHandlers();
        tcpServer.startServer();
        System.out.println("Server shuted down...");
    }
}
