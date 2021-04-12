package by.tc.shop.controller.command.exception;

public class CommandException extends RuntimeException {
    public CommandException() {
        super();
    }
    public CommandException(Exception e){
        super(e);
    }
}
