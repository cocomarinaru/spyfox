package spy.db;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by cocos on 7/16/2017.
 */
public class Row {

    private List<Field> fields;

    public Row() {
        fields = new ArrayList<>();
    }

    public List<Field> getFields() {
        return fields;
    }

    public void add(Field field) {
        fields.add(field);
    }

    @Override
    public String toString() {

        return fields.stream()
                .map(Field::toString)
                .collect(Collectors.joining(","));
    }
}
