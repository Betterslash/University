package ro.ubb.CommunicationCommons;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.TransferServices.ITransferService;

import java.io.*;


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
                this.body ;
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
        String line = br.readLine();
        if(line.contains(StatusCodes.OK.name()) && line.contains(StatusCodes.OK.code.toString())) {
            header.setStatusCode(StatusCodes.OK);
        }else{
            header.setStatusCode(StatusCodes.UNAUTHORIZED);
            return;
        }
        header.setMethodName(br.readLine());
        line = br.readLine();
        if(!this.header.getMethodName().equals(ITransferService.DELETE_STATION_ENTITY) && !this.header.getMethodName().equals(ITransferService.DELETE_TRAIN_ENTITY)){
            while (line != null && !(line).isEmpty()) {
                this.body += line + "\n";
                line = br.readLine();
            }
        }else{
            this.body = line;
        }

    }

    public void writeTo(OutputStream os) throws IOException {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}
