package ro.ubb.CommunicationCommons.CustomEntities;

import lombok.Data;

@Data
public class Header {
    private StatusCodes statusCode;
    private String methodName;
    public Header(StatusCodes statusCode, String methodName){
        this.statusCode = statusCode;
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return
                "statusCode='" + statusCode + '\'' +
                ", methodName='" + methodName + '\'';
    }
}
