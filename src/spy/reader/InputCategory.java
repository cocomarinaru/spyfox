package spy.reader;

import java.util.Map;

public class InputCategory {

    private final String name;
    private Map<String, String> properties;

    public InputCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InputCategory that = (InputCategory) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
