package spy.output;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Category {

    private final String name;
    private List<Property> properties;

    public Category(String name) {
        this.name = name;
        properties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Property> getProperties() {
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

        Category that = (Category) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        String result = "[" + name + "]\n\t";

        String properties = this.properties.stream()
                .map(Property::toString)
                .collect(Collectors.joining("\n\t"));

        return result + properties + '\n';

    }
}
