package exceptions;

public class WeaponException extends Exception {
    private final String reason;

    public WeaponException(String reason) {
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return "Ошибка оружия: " + reason;
    }
}
