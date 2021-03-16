package ro.ubb;

import ro.ubb.TransferServices.ITransferService;
import ro.ubb.tcp.TCPServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        TCPServer tcpServer = new TCPServer(executorService, ITransferService.PORT);
        tcpServer.initializeHandlers();
        tcpServer.startServer();
        System.out.println("Server shuted down...");
    }
}
