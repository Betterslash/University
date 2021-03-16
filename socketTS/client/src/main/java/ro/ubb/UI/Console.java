package ro.ubb.UI;

import ro.ubb.Model.Exceptions.IdTypeException;
import ro.ubb.Model.Exceptions.TimeFormatException;
import ro.ubb.Model.Train;
import ro.ubb.TransferServices.ITransferService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Console {
    private final ITransferService<Integer, Train> iTransferService;
    private final UIPrinter uiPrinter;
    private final Scanner scanner = new Scanner(System.in);
    public Console(ITransferService<Integer, Train> iTransferService) {
        this.iTransferService = iTransferService;
        this.uiPrinter = new UIPrinter();
    }
    public Train createEntity() throws IOException {
        LocalDate localDate;
        int id;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Id >>");
        try {
            id = Integer.parseInt(bufferRead.readLine());
        } catch (Exception e) {
            throw new IdTypeException("The ID should be an int!");
        }
        System.out.println("Type >>");
        String type = null;
        try {
            type = bufferRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Color >>");
        String color = bufferRead.readLine();
        System.out.println("Fabrication date >>");
        try {
            localDate = LocalDate.parse(bufferRead.readLine());
        } catch (Exception e) {
            throw new TimeFormatException("Should be a string with format (yyyy-mm-dd)!");
        }
        return new Train(id, type, color, localDate);
    }
    public void runConsole() throws IOException {

        String choice;
        Future<String> resultFuture;
        while (true) {
            this.uiPrinter.printTrainMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> resultFuture = this.iTransferService.getEntities();
                case "2" -> resultFuture = this.iTransferService.addEntity(this.createEntity());
                case "4" -> resultFuture = this.iTransferService.deleteEntity(Integer.parseInt(scanner.nextLine()));
                case "3" -> resultFuture = this.iTransferService.updateEntity(this.createEntity());
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
            try {
                System.out.println(resultFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
