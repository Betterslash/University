package ro.ubb.Model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
     * @return string representation
     */
    @Override
    public String toString() {
        return "Station{" +
                "ID=" + Id +
                ", stationName='" + stationName + '\'' +
                ", populationRate='" + populationRate + '\'' +
                '}';
    }

    @Override
    public String csvFileFormat() {
        return this.Id+", "+this.stationName +", "+this.populationRate + "\n";
    }

    @Override
    public Node createNodeFromEntity(Document document) {
        Element stationElement = document.createElement("station");

        Element childElement1=document.createElement("id");
        childElement1.setTextContent(String.valueOf(this.Id));
        stationElement.appendChild(childElement1);

        Element childElement2=document.createElement("name");
        childElement2.setTextContent(this.stationName);
        stationElement.appendChild(childElement2);

        Element childElement3=document.createElement("populationRate");
        childElement3.setTextContent(String.valueOf(this.populationRate));
        stationElement.appendChild(childElement3);

        return stationElement;
    }
}
