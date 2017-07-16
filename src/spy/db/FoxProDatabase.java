package spy.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cocos on 7/16/2017.
 */
public class FoxProDatabase implements DBActions {

    private static final Logger LOGGER = LogManager.getLogger(FoxProDatabase.class);

    private static final String DB_DRIVER = "com.hxtt.sql.dbf.DBFDriver";
    private static final String DB_URL_PREFIX = "jdbc:dbf:/";
    private String dbPath = "";

    public FoxProDatabase(String dbPath) {
        this.dbPath = dbPath;
    }

    @Override
    public List<Row> query(String query) {
        Connection connection = connect(this.dbPath);
        if (connection == null) {
            return Collections.emptyList();
        }

        DBContextObjects context = new DBContextObjects();
        context.setConnection(connection);


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            context.setStatement(statement);
            context.setResultSet(resultSet);

            return convertResultSetToRows(resultSet);

        } catch (SQLException e) {
            LOGGER.error("ERRROR: " + e.getMessage(), e);
        }

        context.close();
        return Collections.emptyList();
    }

    private List<Row> convertResultSetToRows(ResultSet resultSet) throws SQLException {
        List<Row> rows = new ArrayList<>();

        ResultSetMetaData metaData = resultSet.getMetaData();

        while (resultSet.next()) {
            Row row = new Row();
            for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                String columnName = metaData.getColumnName(columnIndex);
                Object value = resultSet.getObject(columnIndex);
                Field field = new Field(columnName, value);
                row.add(field);
            }
            rows.add(row);
        }
        return rows;
    }

    private Connection connect(String dbPath) {

        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            String databaseUrl = getDatabaseUrl(dbPath);
            connection = DriverManager.getConnection(databaseUrl);

        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("ERRROR: " + e.getMessage(), e);
        }

        return connection;

    }

    private String getDatabaseUrl(String dbPath) {
        return DB_URL_PREFIX + dbPath;
    }
}
