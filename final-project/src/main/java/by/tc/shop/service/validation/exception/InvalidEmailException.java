package by.tc.shop.service.validation.exception;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(){
        super();
    }
    public InvalidEmailException(String s){
        super(s);
    }
    public InvalidEmailException(Exception e){
        super(e);
    }
}
