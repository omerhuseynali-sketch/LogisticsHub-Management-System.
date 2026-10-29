package logisticshub.exception;

public class SecurityBreachException extends Exception {
    public SecurityBreachException(String message) {
        super("🚨 SECURITY ERROR: " + message);
    }
}
