package ro.ubb.CommunicationCommons;

import lombok.Data;
import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.Model.Exceptions.MessageException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Message {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private Header header;
    private String body;
    public Message(){
        this.header = new Header(StatusCodes.OK, "");
        this.body = "";
    }
    public Message(Header header, String body){
        this.header = header;
        this.body = body;
    }

    @Override
    public String toString() {
        return header.getStatusCode() + "\n" +
                header.getMethodName() + "\n" +
                this.body;
    }


    public void readFrom(InputStream is) throws IOException {
        List<String> streamLines = new ArrayList<>();
        BufferedReader isReader = new BufferedReader(new InputStreamReader(is));
        String lines = isReader.readLine();
        while(lines != null && !lines.isEmpty()){
            streamLines.add(lines);
            lines = isReader.readLine();
        }
        this.header = new Header(StatusCodes.OK, streamLines
                .stream()
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new MessageException("Couldn't parse correctly!")));
        streamLines.stream()
                .skip(2)
                .forEach(e -> this.body += e + LINE_SEPARATOR);
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}
