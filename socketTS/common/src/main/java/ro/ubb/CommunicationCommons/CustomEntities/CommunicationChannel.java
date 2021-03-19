package ro.ubb.CommunicationCommons.CustomEntities;

import ro.ubb.Model.Exceptions.ChannelExceptions.ChannelException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CommunicationChannel{
    private static final String commonPath = "";
    private static final String inPath = commonPath + "in.txt";
    private static final String outPath = commonPath + "out.txt";
    public static List<String> readChannel(){
        try {
            return Files.readAllLines(Paths.get(inPath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ChannelException("Something went wrong furing reading the channel!");
        }
    }
    public static void writeChannel(String message){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inPath));
            bufferedWriter.write(message);
            bufferedWriter.close();
        }
        catch (IOException e){
            throw new ChannelException("Something went wrong during writing!");
        }
    }
}
