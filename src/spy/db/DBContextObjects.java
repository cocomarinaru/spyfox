package spy.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by cocos on 7/16/2017.
 */
public class DBContextObjects {

    private static final Logger LOGGER = LogManager.getLogger();

    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;

    public DBContextObjects() {
    }

    public DBContextObjects(Statement statement, ResultSet resultSet, Connection connection) {
        this.statement = statement;
        this.resultSet = resultSet;
        this.connection = connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void close() {

        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            LOGGER.error("ERROR" + e.getMessage(), e);
        } finally {

            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                LOGGER.error("ERROR" + e.getMessage(), e);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                LOGGER.error("ERROR" + e.getMessage(), e);
            }
        }
    }
}
