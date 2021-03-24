package ro.ubb.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Train extends BaseEntity<Integer>{
    private final Integer id;
    private final String trainType;
    private final String trainColor;
    private final LocalDate date;

    /**
     * @param id train id
     * @param trainType the type of a train
     * @param trainColor color
     * @param date date
     */
    public Train(Integer id, String trainType, String trainColor, LocalDate date){
        this.id = id;
        this.trainType = trainType;
        this.trainColor = trainColor;
        this.date = date;
    }

    /**
     * @return string representation
     */
    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", trainType='" + trainType + '\'' +
                ", trainColor='" + trainColor + '\'' +
                ", date='" + date.toString() + '\'' +
                '}';
    }

    /**
     *
     * @returns the CSV-File-Format of the entity
     */
    @Override
    public String csvFileFormat() {
        return this.id+", "+this.trainType+", "+this.trainColor+", "+this.date + "\n";
    }

}
