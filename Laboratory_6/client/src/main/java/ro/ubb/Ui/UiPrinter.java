package ro.ubb.Ui;

public class UiPrinter {
    public final static String TRAIN_CONSTANT = "1";
    public final static String STATION_CONSTANT = "2";
    public final static String TT_CONSTANT = "3";
    public final static String FEATURE_CONSTANT = "4";
    public final static String EXIT_VALUE = "X";

    /**
     * prints the main menu of the application
     */
    public static void printMainMenu(){
        System.out.println("1 -> Trains menu!");
        System.out.println("2 -> Stations menu!");
        System.out.println("3 -> Time tables menu!");
        System.out.println("4 -> Feature menu!");
        System.out.println("X -> Exit!");
    }

    /**
     * prints the menu that operates with Train entity
     */
    public static void printTrainMenu(){
        System.out.println("1 -> Get all trains!");
        System.out.println("2 -> Add a train!");
        System.out.println("3 -> Update a train!");
        System.out.println("4 -> Delete a train!");
        System.out.println("X -> Back!");
    }

    /**
     * prints the menu that operates with Station entity
     */
    public static void printStationMenu(){
        System.out.println("1 -> Get all stations!");
        System.out.println("2 -> Add a station!");
        System.out.println("3 -> Update a station!");
        System.out.println("4 -> Delete a station!");
        System.out.println("X -> Back!");
    }

    /**
     * prints the menu that operates with TimeTable entity
     */
    public static void printTimeTablesMenu(){
        System.out.println("1 -> Get all time tables!");
        System.out.println("2 -> Add a time table!");
        System.out.println("3 -> Update a time table!");
        System.out.println("4 -> Delete a time table!");
        System.out.println("X -> Back!");
    }

    /**
     * prints the menu for featured commands
     */
    public static void printFeatureMenu()
    {
        System.out.println("1 -> Get trains passing every station!");
        System.out.println("2 -> Get stations passed by every train!");
        System.out.println("3 -> Get most traveled station!");
        System.out.println("X -> Back!");
    }
}
