package spy.reader;

import java.util.ArrayList;
import java.util.List;

public class InputCategory {

    private final String name;
    private List<InputProperty> properties;

    public InputCategory(String name) {
        this.name = name;
        properties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<InputProperty> getProperties() {
        return properties;
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
