package ro.ubb.UI;

import ro.ubb.Model.Exceptions.IdTypeException;
import ro.ubb.Model.Exceptions.TimeFormatException;
import ro.ubb.Model.Station;
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
    private final ITransferService<Integer, Train> trainTransferService;
    private final ITransferService<Integer, Station> stationTransferService;
    private final Scanner scanner = new Scanner(System.in);
    public Console(ITransferService<Integer, Train> iTransferService, ITransferService<Integer, Station> stationTransferService) {
        this.trainTransferService = iTransferService;
        this.stationTransferService = stationTransferService;
    }
    public Station createStationEntity() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Id >>");
        int id;
        try {
            id = Integer.parseInt(bufferRead.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new IdTypeException("The ID should be an int!");
        }
        System.out.println("Name >>");
        String name = bufferRead.readLine();
        System.out.println("Population rate >>");
        String populationRate = bufferRead.readLine();
        return new Station(id, name, populationRate.strip());
    }
    public Train createTrainEntity() throws IOException {
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
        Future<String> resultFuture = null;
        while (true) {
            UIPrinter.printMainMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case UIPrinter.TRAIN_CONSTANT -> {
                    UIPrinter.printTrainMenu();
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> resultFuture = this.trainTransferService.getEntities();
                        case "2" -> resultFuture = this.trainTransferService.addEntity(this.createTrainEntity());
                        case "4" -> resultFuture = this.trainTransferService.deleteEntity(Integer.parseInt(scanner.nextLine()));
                        case "3" -> resultFuture = this.trainTransferService.updateEntity(this.createTrainEntity());
                        default -> throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }
                case UIPrinter.STATION_CONSTANT -> {
                    UIPrinter.printStationMenu();
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> resultFuture = this.stationTransferService.getEntities();
                        case "2" -> resultFuture = this.stationTransferService.addEntity(this.createStationEntity());
                        case "4" -> resultFuture = this.stationTransferService.deleteEntity(Integer.parseInt(scanner.nextLine()));
                        case "3" -> resultFuture = this.stationTransferService.updateEntity(this.createStationEntity());
                        default -> throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }
            }

            try {
                assert resultFuture != null;
                System.out.println(resultFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
