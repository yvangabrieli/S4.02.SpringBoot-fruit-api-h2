package cat.itacademy.s04.t02.n01.fruit.exception;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message){
        super(message);
    }
}
