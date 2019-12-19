package pl.michonskim.works.exception;

public class MyException extends RuntimeException {

    private final String message;

    public MyException(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "message='" + message + '\'' +
                '}';
    }
}
