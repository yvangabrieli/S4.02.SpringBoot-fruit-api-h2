package cat.itacademy.s04.t02.n01.fruit_api_h2.exception;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message){
        super(message);
    }
}
