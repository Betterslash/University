package ro.ubb.UI;

public class UIPrinter {
    public final static String TRAIN_CONSTANT = "1";
    public final static String STATION_CONSTANT = "2";
    public static void printMainMenu(){
        System.out.println("1 -> Trains menu!");
        System.out.println("2 -> Stations menu!");
    }
    public static void printTrainMenu(){
        System.out.println("1 -> Get all trains!");
        System.out.println("2 -> Add a train!");
        System.out.println("3 -> Update a train!");
        System.out.println("4 -> Delete a train!");
    }
    public static void printStationMenu(){
        System.out.println("1 -> Get all stations!");
        System.out.println("2 -> Add a station!");
        System.out.println("3 -> Update a station!");
        System.out.println("4 -> Delete a station!");
    }
}
