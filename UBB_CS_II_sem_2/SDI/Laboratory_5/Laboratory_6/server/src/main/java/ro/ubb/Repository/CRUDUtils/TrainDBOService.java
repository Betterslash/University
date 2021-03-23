package ro.ubb.Repository.CRUDUtils;

import ro.ubb.Model.Train;
import ro.ubb.Repository.DBOUtils.DBOServices;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TrainDBOService extends DBOServices<Integer, Train> {
    private final static String READ_ENTITIES = "SELECT * FROM trains";
    private final static String ADD_ENTITY = "INSERT INTO trains (trainid, type, color, fabrication_date) VALUES (?,?,?,?);";
    private final static String DELETE_ENTITY = "DELETE FROM trains WHERE trainid = ?;";
    private final static String UPDATE_ENTITY = "UPDATE trains SET type = ?, color = ?, fabrication_date = ? WHERE trainid = ?;";

    @Override
    public Map<Integer, Train> getEntities() {
        Map<Integer, Train> trainMap = new HashMap<>();
        this.jdbcOperations.query(READ_ENTITIES, (res, i) -> {
            Integer id = res.getInt("trainid");
            String type = res.getString("type");
            String color = res.getString("color");
            LocalDate localDate = LocalDate.parse(res.getString("fabrication_date"));
            return new Train(id, type, color, localDate);
        }).forEach(e -> trainMap.put(e.getId(), e));
        return trainMap;
    }
    /**
     * adds a station in the database
     * @param entity entity to be saved
     */
    @Override
    public void saveEntity(Train entity) {
        this.jdbcOperations.update(ADD_ENTITY, entity.getId(), entity.getTrainType(), entity.getTrainColor(), entity.getDate());
    }

    /**
     * deletes a station from the database
     * @param id id of deletion entity
     */
    @Override
    public void deleteEntity(Integer id) {
        this.jdbcOperations.update(DELETE_ENTITY, id);
    }

    /**
     * updates a station in the database
     * @param entity entity to be updated
     */
    @Override
    public void updateEntity(Train entity) {
        this.jdbcOperations.update(UPDATE_ENTITY, entity.getTrainType(), entity.getTrainColor(), entity.getDate(), entity.getId());
    }
}
