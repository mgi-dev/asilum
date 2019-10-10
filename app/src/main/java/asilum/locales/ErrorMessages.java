package asilum.locales;

public enum ErrorMessages {
    USER_ALREADY_EXISTING ("User already existing.");

    public final String label;

    private ErrorMessages(String label) {
        this.label = label;
    }
}
