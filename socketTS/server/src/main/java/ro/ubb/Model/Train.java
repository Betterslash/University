package ro.ubb.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
}
