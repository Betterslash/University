package ro.ubb.Repository.Repositories.CRUDUtils;



import ro.ubb.Model.Exceptions.DBOServiceExceptions.StationDBOServiceException;
import ro.ubb.Model.Station;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StationDBOService extends DBOServices<Integer, Station> {
    /**
     *
     * @return a map containing all entities' IDs and the entities
     */
    @Override
    public Map<Integer, Station> getEntities() {
        try {
            Map<Integer, Station> resultMap = new HashMap<>();
            var connection = DriverManager.getConnection(super.url, super.user, super.password);
            var stmt = connection.prepareStatement("SELECT * FROM Stations");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String stationName = resultSet.getString(2);
                String populationRate = resultSet.getString(3);
                Station station = new Station(id, stationName, populationRate);
                resultMap.put(id, station);
            }
            return resultMap;
        }catch (SQLException sqlException){
            throw new StationDBOServiceException("Couldn't get entities from Stations table");
        }
    }

    /**
     * adds a station in the database
     * @param entity entity to be saved
     */
    @Override
    public void saveEntity(Station entity) {
        try{
        var connection = DriverManager.getConnection(super.url, super.user, super.password);
        var stmt = connection.prepareStatement("INSERT INTO Stations(StationID, station_name, population_rate) VALUES (?, ?, ?);");
        stmt.setInt(1, entity.getId());
        stmt.setString(2, entity.getStationName());
        stmt.setString(3, entity.getPopulationRate());

            stmt.executeUpdate();
        }catch (SQLException sqlException){
            throw new StationDBOServiceException("Couldn't execute INSERT in Stations Table !");
        }
    }

    /**
     * deletes a station from the database
     * @param id id of deletion entity
     */
    @Override
    public void deleteEntity(Integer id) {
        try{
        var connection = DriverManager.getConnection(super.url, super.user, super.password);
        var stmt = connection.prepareStatement("DELETE FROM Stations WHERE StationID = ?");
        stmt.setInt(1, id);

            stmt.executeUpdate();
        }catch (SQLException sqlException){
            throw new StationDBOServiceException("Couldn't execute DELETE in Stations Table !");
        }
    }

    /**
     * updates a station in the database
     * @param entity entity to be updated
     */
    @Override
    public void updateEntity(Station entity) {
        try{
        var connection = DriverManager.getConnection(super.url, super.user, super.password);
        var stmt = connection.prepareStatement("UPDATE Stations SET station_name = ?, population_rate =? WHERE StationID = ?;");
        stmt.setInt(3, entity.getId());
        stmt.setString(1, entity.getStationName());
        stmt.setString(2, entity.getPopulationRate());

            stmt.executeUpdate();
        }catch (SQLException sqlException){
            throw new StationDBOServiceException("Couldn't execute UPDATE in Stations Table !");
        }
    }
}
