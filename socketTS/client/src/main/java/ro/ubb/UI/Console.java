package ro.ubb.UI;

import ro.ubb.Model.Train;
import ro.ubb.TransferServices.ITransferService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Console {
    private final ITransferService<Integer, Train> iTransferService;

    public Console(ITransferService<Integer, Train> iTransferService) {
        this.iTransferService = iTransferService;
    }

    public void runConsole() {
        //todo: implement a menu or cmd based ui

        Future<String> resultFuture = iTransferService.getEntities(); //non-blocking

        /*
        .....
         */

        try {
            System.out.println(resultFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
