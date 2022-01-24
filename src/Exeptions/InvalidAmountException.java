package Exeptions;

public class InvalidAmountException extends RuntimeException {
    public static final String Message = "That's too much! try again";
    public InvalidAmountException() {
        super(Message);
    }
}
