package by.tc.shop.service.validation.exception;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(){
        super();
    }
    public InvalidLoginException(String s){
        super(s);
    }
    public InvalidLoginException(Exception e){
        super(e);
    }
}
