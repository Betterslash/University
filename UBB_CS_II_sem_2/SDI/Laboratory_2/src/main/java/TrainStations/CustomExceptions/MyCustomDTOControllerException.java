package TrainStations.CustomExceptions;

public class MyCustomDTOControllerException extends Exception{
    public MyCustomDTOControllerException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
