package ro.ubb.CommunicationCommons.CustomEntities;

public enum StatusCodes {
    OK(200), UNAUTHORIZED(401), SERVER_ERROR(500);
    public final Integer code;
    StatusCodes(Integer code){
        this.code = code;
    }

    @Override
    public String toString() {
        return  this.name() + " " + code;
    }
}
