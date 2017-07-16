package spy.input;

public class InputProperty {

    private final String key;
    private final String value;

    public InputProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
