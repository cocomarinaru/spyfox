package spy.db;

import java.util.Objects;

/**
 * Created by cocos on 7/16/2017.
 */
public class Field {

    private final String columnName;
    private final Object value;

    public Field(String columnName, Object value) {
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ")
                .append(columnName)
                .append(":")
                .append(Objects.toString(value, "EMPTY"))
                .append(" ]");
        return sb.toString();
    }

}
