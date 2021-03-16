package ro.ubb.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @param <ID>
 */
public abstract class BaseEntity<ID> {
    private ID id;

    /**
     * @return id
     */
    public ID getId() {
        return id;
    }

    /**
     * @param id id of every entity
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * @return string representation
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }

    /**
     * @return the proper format of every object inheriting this class to be stored as a csv entry
     */
    public abstract String csvFileFormat();
    public  abstract Node createNodeFromEntity(Document document);
}
