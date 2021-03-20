package ro.ubb.Repository.Repositories.CRUDUtils;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.TimeTableDBOServiceException;
import ro.ubb.Model.TrainsStationsEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTableDBOService extends DBOServices<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>{
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @return a map containing all entities' IDs and the entities
     */
    @Override
    public Map<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> getEntities() {
        try{
            Map<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> trainsStationsMap = new HashMap<>();
            var connection = DriverManager.getConnection(url, user, password);
            var stmt = connection.prepareStatement("SELECT * FROM TimeTables;");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Integer trainID = resultSet.getInt(1);
                Integer stationID = resultSet.getInt(2);
                Pair<Integer, Integer> ID = new Pair<>(trainID, stationID);
                String arrivalDate = resultSet.getDate(3).toString();
                String arrivalTime = resultSet.getTime(5).toString();
                LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalDate + " " + arrivalTime, formatter);
                String departureDate = resultSet.getDate(4).toString();
                String departureTime = resultSet.getTime(6).toString();
                LocalDateTime departureDateTime = LocalDateTime.parse(departureDate + " " + departureTime, formatter);
                trainsStationsMap.put(ID, new TrainsStationsEntity<>(ID, arrivalDateTime, departureDateTime));
        }
        return trainsStationsMap;
        }catch (SQLException runtimeException){
            throw new TimeTableDBOServiceException("Failed to get TimeTables table!");
        }
    }

    /**
     * adds a timetable in the database
     * @param entity
     */
    @Override
    public void saveEntity(TrainsStationsEntity<Integer, Integer> entity) {
        try{
            var connection = DriverManager.getConnection(url, user, password);
            var stmt = connection.prepareStatement("INSERT INTO TimeTables(TrainID, StationID, arrival_date, departure_date, arrival_time, departure_time) VALUES (?, ?, ?, ?, ?, ?);");
            stmt.setInt(1, entity.getId().getFirst());
            stmt.setInt(2, entity.getId().getLast());
            List<String> arrivalTimes = Arrays.asList(entity.getArrivalTime().toString().split("T"));
            System.out.println(arrivalTimes);
            List<String> departureTimes = Arrays.asList(entity.getDepartureTime().toString().split("T"));
            stmt.setDate(3, Date.valueOf(arrivalTimes.get(0)));
            stmt.setDate(4, Date.valueOf(departureTimes.get(0)));
            stmt.setTime(5, Time.valueOf(arrivalTimes.get(1)));
            stmt.setTime(6, Time.valueOf(departureTimes.get(1)));
            stmt.executeUpdate();
        }
        catch (SQLException sqlException){
            throw new TimeTableDBOServiceException("Could't INSERT entity in TimeTables table!");
        }
    }

    /**
     * deletes a timetable from the database
     * @param id
     */
    @Override
    public void deleteEntity(Pair<Integer, Integer> id) {
        try{
            var connection = DriverManager.getConnection(super.url, super.user, super.password);
            var stmt = connection.prepareStatement("DELETE FROM TimeTables WHERE TrainID = ? AND StationID = ?");
            stmt.setInt(1, id.getFirst());
            stmt.setInt(2, id.getLast());
            stmt.executeUpdate();
        }catch (SQLException sqlException){
            throw new TimeTableDBOServiceException("Couldn't execute DELETE in TimeTables table !");
        }
    }

    /**
     * updates a timetable in the database
     * @param entity
     */
    @Override
    public void updateEntity(TrainsStationsEntity<Integer, Integer> entity) {
        try{
            var connection = DriverManager.getConnection(url, user, password);
            var stmt = connection.prepareStatement("UPDATE TimeTables SET arrival_date = ?, departure_date = ?, arrival_time = ?, departure_time =? WHERE TrainID = ? AND StationID = ?;");
            stmt.setInt(5, entity.getId().getFirst());
            stmt.setInt(6, entity.getId().getLast());
            List<String> arrivalTimes = Arrays.asList(entity.getArrivalTime().toString().split("T"));
            System.out.println(arrivalTimes);
            List<String> departureTimes = Arrays.asList(entity.getDepartureTime().toString().split("T"));
            stmt.setDate(1, Date.valueOf(arrivalTimes.get(0)));
            stmt.setDate(2, Date.valueOf(departureTimes.get(0)));
            stmt.setTime(3, Time.valueOf(arrivalTimes.get(1)));
            stmt.setTime(4, Time.valueOf(departureTimes.get(1)));
            stmt.executeUpdate();
        }
        catch (SQLException sqlException){
            throw new TimeTableDBOServiceException("Could't UPDATE entity in TimeTables table!");
        }
    }
}
