package by.tc.shop.service.validation.exception;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(){
        super();
    }
    public InvalidPasswordException(String s){
        super(s);
    }
    public InvalidPasswordException(Exception e){
        super(e);
    }
}
