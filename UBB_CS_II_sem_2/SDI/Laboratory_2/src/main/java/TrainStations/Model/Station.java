package TrainStations.Model;

public class Station implements DTOCustomObj{
    private final int id;
    private final String name;
    private final int populationRate;

    public String getName() {
        return name;
    }

    public int getPopulationRate() {
        return populationRate;
    }

    public int getId() {
        return id;
    }

    public Station(int id, String name, int populationRate) {
        this.id = id;
        this.name = name;
        this.populationRate = populationRate;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", populationRate=" + populationRate +
                '}';
    }
}
