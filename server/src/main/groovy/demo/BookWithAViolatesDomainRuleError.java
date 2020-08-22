package demo;

public class BookWithAViolatesDomainRuleError extends Exception {
    public BookWithAViolatesDomainRuleError(String message) {
        super(message);
    }
}