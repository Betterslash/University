package ro.ubb.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ro.ubb.Model.Validators.TrainValidator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public String csvFileFormat() {
        return this.id+", "+this.trainType+", "+this.trainColor+", "+this.date + "\n";
    }

    @Override
    public Node createNodeFromEntity(Document document) {
        Element trainElement = document.createElement("train");

        Element childElement1=document.createElement("id");
        childElement1.setTextContent(String.valueOf(this.id));
        trainElement.appendChild(childElement1);

        Element childElement2=document.createElement("type");
        childElement2.setTextContent(this.trainType);
        trainElement.appendChild(childElement2);

        Element childElement3=document.createElement("color");
        childElement3.setTextContent(String.valueOf(this.trainColor));
        trainElement.appendChild(childElement3);

        Element childElement4=document.createElement("fabricationDate");
        childElement4.setTextContent(String.valueOf(this.date));
        trainElement.appendChild(childElement4);

        return trainElement;
    }
    public static Train parseTrain(String csvFormat){
        List<String> items = Arrays.asList(csvFormat.split(", "));
        Integer id = Integer.parseInt(items.get(0));
        String type = items.get(1);
        String color = items.get(2);
        LocalDate localDate = LocalDate.parse(items.get(3).strip());
        Train train = new Train(id, type, color, localDate);
        new TrainValidator().validate(train);
        return train;
    }
}
