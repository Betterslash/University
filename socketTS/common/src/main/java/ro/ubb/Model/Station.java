package ro.ubb.Model;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Station extends BaseEntity<Integer>{
        private Integer Id;
        private String stationName;
        private String populationRate;

        public Station(Integer ID, String stationName, String populationRate)
        {
            this.Id = ID;
            this.stationName = stationName;
            this.populationRate = populationRate;
        }


    /**
     * @returns string representation
     */
    @Override
    public String toString() {
        return "Station{" +
                "ID=" + Id +
                ", stationName='" + stationName + '\'' +
                ", populationRate='" + populationRate + '\'' +
                '}';
    }

    /**
     *
     * @returns the CSV-File-Format of the entity
     */
    @Override
    public String csvFileFormat() {
        return this.Id+", "+this.stationName +", "+this.populationRate + "\n";
    }

}
