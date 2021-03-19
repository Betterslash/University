package ro.ubb.Repository.Repositories.CRUDUtils;

import ro.ubb.Model.Exceptions.TrainDBOServiceException;
import ro.ubb.Model.Train;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TrainDBOService extends DBOServices<Integer, Train> {

    /**
     *
     * @return a map containing all entities' IDs and the entities
     */
    @Override
    public Map<Integer, Train> getEntities() {
       try {
           Map<Integer, Train> resultMap = new HashMap<>();
           var connection = DriverManager.getConnection(super.url, super.user, super.password);
           var stmt = connection.prepareStatement("SELECT * FROM Trains");
           ResultSet resultSet = stmt.executeQuery();
           while (resultSet.next()) {
               Integer ID = resultSet.getInt(1);
               String type = resultSet.getString(2);
               String color = resultSet.getString(3);
               LocalDate fabricationDate = LocalDate.parse(resultSet.getString(4));
               resultMap.put(ID, new Train(ID, type, color, fabricationDate));
           }
           return resultMap;
       }catch (SQLException sqlException){
           throw new TrainDBOServiceException("Could't get Trains from DB !");
       }
    }

    /**
     * adds a train in the database
     * @param entity entity to be saved
     */
    @Override
    public void saveEntity(Train entity) {
        try{
        var connection = DriverManager.getConnection(super.url, super.user, super.password);
        var stmt = connection.prepareStatement("INSERT INTO Trains(TrainID, type, color, fabrication_date) VALUES (?, ?, ?, ?);");
        stmt.setInt(1, entity.getId());
        stmt.setString(2, entity.getTrainType());
        stmt.setString(3, entity.getTrainColor());
        stmt.setDate(4, Date.valueOf(entity.getDate()));

            stmt.executeUpdate();
        }catch (SQLException sqlException){
            throw new TrainDBOServiceException("Couldn't execute INSERT in Trains table !");
        }
    }

    /**
     * deletes a train from the database
     * @param id id of the deleted entity
     */
    @Override
    public void deleteEntity(Integer id) {
        try{
        var connection = DriverManager.getConnection(super.url, super.user, super.password);
        var stmt = connection.prepareStatement("DELETE FROM Trains WHERE TrainID = ?");
        stmt.setInt(1, id);

            stmt.executeUpdate();
        }catch (SQLException sqlException){
            throw new TrainDBOServiceException("Couldn't execute DELETE in Trains table !");
        }

    }

    /**
     * updates a train in the database
     * @param entity entity to be updated
     */
    @Override
    public void updateEntity(Train entity) {
        try{
        var connection = DriverManager.getConnection(super.url, super.user, super.password);
        var stmt = connection.prepareStatement("UPDATE Trains SET type = ?, color = ?, fabrication_date =? WHERE TrainID = ?;");
        stmt.setInt(4, entity.getId());
        stmt.setString(1, entity.getTrainType());
        stmt.setString(2, entity.getTrainColor());
        stmt.setDate(3, Date.valueOf(entity.getDate()));

            stmt.executeUpdate();
        }catch (SQLException sqlException){
            throw new TrainDBOServiceException("Couldn't execute UPDATE in Trains table !");
        }
    }
}
