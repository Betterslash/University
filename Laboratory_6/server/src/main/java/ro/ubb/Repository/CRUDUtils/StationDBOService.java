package ro.ubb.Repository.CRUDUtils;


import ro.ubb.Model.Station;
import ro.ubb.Repository.DBOUtils.DBOServices;

import java.util.HashMap;
import java.util.Map;

public class StationDBOService extends DBOServices<Integer, Station> {


    private final static String READ_ENTITIES = "SELECT * FROM stations";
    private final static String ADD_ENTITY = "INSERT INTO stations (stationid, station_name, population_rate) VALUES (?,?,?);";
    private final static String DELETE_ENTITY = "DELETE FROM stations WHERE stationid = ?;";
    private final static String UPDATE_ENTITY = "UPDATE stations SET station_name = ?, population_rate = ? WHERE stationid = ?;";


    @Override
    public Map<Integer, Station> getEntities() {
        Map<Integer, Station> stationMap = new HashMap<>();
        this.jdbcOperations.query(READ_ENTITIES, (res, i) -> {
            Integer id = res.getInt("stationid");
            String name = res.getString("station_name");
            String populationRate = res.getString("population_rate");
            return new Station(id, name, populationRate);
        }).forEach(e -> stationMap.put(e.getId(), e));
        return stationMap;
    }

    /**
     * adds a station in the database
     * @param entity entity to be saved
     */
    @Override
    public void saveEntity(Station entity) {
        this.jdbcOperations.update(ADD_ENTITY, entity.getId(), entity.getStationName(), entity.getPopulationRate());
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
    public void updateEntity(Station entity) {
        this.jdbcOperations.update(UPDATE_ENTITY, entity.getStationName(), entity.getPopulationRate(), entity.getId());
    }
}
