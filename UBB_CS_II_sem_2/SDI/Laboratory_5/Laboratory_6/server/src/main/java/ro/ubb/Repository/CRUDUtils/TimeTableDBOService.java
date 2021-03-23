package ro.ubb.Repository.CRUDUtils;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.DBOUtils.DBOServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTableDBOService extends DBOServices<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static String READ_ENTITIES = "SELECT * FROM timetables";
    private final static String ADD_ENTITY = "INSERT INTO timetables (trainid, stationid, arrival_date, departure_date, arrival_time, departure_time) VALUES (?, ?, ?, ?, ?, ?);";
    private final static String DELETE_ENTITY = "DELETE FROM timetables WHERE trainid = ? AND stationid = ?";
    private final static String UPDATE_ENTITY = "UPDATE timetables SET arrival_date = ?,  departure_date = ?, arrival_time = ?, departure_time = ? WHERE trainid = ? AND stationid = ?;";


    @Override
    public Map<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> getEntities() {
        Map<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> stationMap = new HashMap<>();
        this.jdbcOperations.query(READ_ENTITIES, (res, i) -> {
            Pair<Integer, Integer> id = new Pair<>(res.getInt("trainid"), res.getInt("stationid"));
            String arrivalDate = res.getDate("arrival_date").toString();
            String arrivalTime = res.getTime("arrival_time").toString();
            LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalDate + " " + arrivalTime, formatter);
            String departureDate = res.getDate("departure_date").toString();
            String departureTime = res.getTime("departure_time").toString();
            LocalDateTime departureDateTime = LocalDateTime.parse(departureDate + " " + departureTime, formatter);
            return new TrainsStationsEntity<>(id, LocalDateTime.parse(arrivalDate + " " + arrivalTime, formatter),
                    LocalDateTime.parse(departureDate + " " + departureTime, formatter));
        }).forEach(e -> stationMap.put(e.getId(), e));
        return stationMap;
    }

    /**
     * adds a station in the database
     * @param entity entity to be saved
     */
    @Override
    public void saveEntity(TrainsStationsEntity<Integer, Integer> entity) {
        createDateTimes(entity, ADD_ENTITY);
    }

    /**
     * deletes a station from the database
     * @param id id of deletion entity
     */
    @Override
    public void deleteEntity(Pair<Integer, Integer> id) {
        this.jdbcOperations.update(DELETE_ENTITY, id.getFirst(), id.getLast());
    }

    /**
     * updates a station in the database
     * @param entity entity to be updated
     */
    @Override
    public void updateEntity(TrainsStationsEntity<Integer, Integer> entity) {
        createDateTimes(entity, UPDATE_ENTITY);
    }

    private void createDateTimes(TrainsStationsEntity<Integer, Integer> entity, String updateEntity) {
        List<String> arrivalTimes = Arrays.asList(entity.getArrivalTime().toString().split("T"));
        List<String> departureTimes = Arrays.asList(entity.getDepartureTime().toString().split("T"));
        this.jdbcOperations.update(updateEntity, entity.getId(), entity.getArrivalTime(), arrivalTimes.get(0), arrivalTimes.get(1), departureTimes.get(0), departureTimes.get(1));
    }
}
