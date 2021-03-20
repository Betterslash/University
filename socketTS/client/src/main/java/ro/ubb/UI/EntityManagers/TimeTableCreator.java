package ro.ubb.UI.EntityManagers;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.DomainExceptions.IdTypeException;
import ro.ubb.Model.Exceptions.DomainExceptions.TimeFormatException;
import ro.ubb.Model.TrainsStationsEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTableCreator extends IEntityCreator<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>{

    /**
     * creates an entity of type TimeTable
     * @returns the new created timetable
     */
    @Override
    public TrainsStationsEntity<Integer, Integer> createEntity() {
        int trainID, stationID;
        LocalDateTime arrivalTime, departureTime;
        System.out.println("TrainID >>");
        try {
            trainID = Integer.parseInt(bufferRead.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new IdTypeException("Train ID should be an int!");
        }
        System.out.println("StationID >>");
        try {
            stationID = Integer.parseInt(bufferRead.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new IdTypeException("Station ID should be an int!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Arrival Time >>");
        try {
            arrivalTime = LocalDateTime.parse(bufferRead.readLine(), formatter);
        } catch (Exception e) {
            throw new TimeFormatException("Should be a string with format (yyyy-mm-dd hh:mm:ss)!");
        }
        System.out.println("Departure Time >>");
        try {
            departureTime = LocalDateTime.parse(bufferRead.readLine(), formatter);
        } catch (Exception e) {
            throw new TimeFormatException("Should be a string with format (yyyy-mm-dd hh:mm:ss)!");
        }
        return new TrainsStationsEntity<>(new Pair<>(trainID, stationID), arrivalTime, departureTime);
    }


    @Override
    public Pair<Integer, Integer> createID() {
        int trainId, stationId;
        System.out.println("Give here the train id >>");
        try {
            trainId = Integer.parseInt(bufferRead.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new IdTypeException("Train ID should be an int!");
        }
        System.out.println("Give here the station id >>");
        try {
            stationId = Integer.parseInt(bufferRead.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new IdTypeException("Station ID should be an int!");
        }
        return new Pair<>(trainId, stationId);
    }
}
