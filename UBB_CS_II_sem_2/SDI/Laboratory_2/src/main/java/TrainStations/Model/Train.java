package TrainStations.Model;

public class Train implements DTOCustomObj{
    private final int id;
    private final String type;
    private final String color;
    private final String fabricationDate;

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getFabricationDate() {
        return fabricationDate;
    }

    public Train(int id, String type, String color, String fabricationDate){
        this.id = id;
        this.type = type;
        this.color = color;
        this.fabricationDate = fabricationDate;
    }

    @Override
    public String toString() {
        return "Train{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", fabricationDate='" + fabricationDate + '\'' +
                '}';
    }
}
