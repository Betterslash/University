package ro.ubb.UI.EntityManagers;

import ro.ubb.Model.Exceptions.IdTypeException;
import ro.ubb.Model.Exceptions.TimeFormatException;
import ro.ubb.Model.Train;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class TrainCreator extends IEntityCreator<Integer, Train> {
    @Override
    public Train createEntity() {
        LocalDate localDate;
        int id;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Id >>");
        try {
            id = Integer.parseInt(bufferRead.readLine());
        } catch (Exception e) {
            throw new IdTypeException("The ID should be an int!");
        }
        System.out.println("Type >>");
        String type = null;
        try {
            type = bufferRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Color >>");
        String color = null;
        try {
            color = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Fabrication date >>");
        try {
            localDate = LocalDate.parse(bufferRead.readLine());
        } catch (Exception e) {
            throw new TimeFormatException("Should be a string with format (yyyy-mm-dd)!");
        }
        return new Train(id, type, color, localDate);
    }

    @Override
    public Integer createID() {
        System.out.println("Give here the train id >>");
        Integer id = null;
        try {
            id = Integer.parseInt(super.bufferRead.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
}
