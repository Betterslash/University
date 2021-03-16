package ro.ubb.CommunicationCommons;

import lombok.Data;
import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;

import java.io.*;


public class Message {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private Header header;
    private String body;
    public Message(){

    }
    public Message(Header header, String body){
        this.header = header;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message = {" +
                " Header = { " + header +"}" +
                "; Body= {" + body + " }"+ '\'' +
                '}';
    }

    public Header getHeader() {
        return header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void readFrom(InputStream is) throws IOException {
        var br = new BufferedReader(new InputStreamReader(is));
        header.setStatusCode(StatusCodes.OK);
        header.setMethodName(br.readLine());
        body = br.readLine();
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}
