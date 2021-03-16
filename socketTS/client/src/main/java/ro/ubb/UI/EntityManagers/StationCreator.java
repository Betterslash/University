package ro.ubb.UI.EntityManagers;

import ro.ubb.Model.Exceptions.IdTypeException;
import ro.ubb.Model.Station;

import java.io.IOException;

public class StationCreator extends IEntityCreator<Integer, Station> {

    @Override
    public Station createEntity() {
        System.out.println("Id >>");
        int id;
        try {
            id = Integer.parseInt(bufferRead.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new IdTypeException("The ID should be an int!");
        }
        System.out.println("Name >>");
        String name = null;
        try {
            name = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Population rate >>");
        String populationRate = null;
        try {
            populationRate = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert populationRate != null;
        return new Station(id, name, populationRate.strip());
    }

    @Override
    public Integer createID() {
        System.out.println("Give here a station id >>");
        int id = 0;
        try {
            id = Integer.parseInt(super.bufferRead.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
}
