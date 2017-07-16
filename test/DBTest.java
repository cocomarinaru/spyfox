import spy.Config;
import spy.db.Field;
import spy.db.FoxProDatabase;
import spy.db.Row;

import java.util.List;

/**
 * Created by cocos on 7/16/2017.
 */
public class DBTest {


    public static void main(String[] args) {

        String databasePath = Config.getDatabasePath();

        System.out.println("Database path: " + databasePath);

        FoxProDatabase foxProDatabase = new FoxProDatabase(databasePath);

        List<Row> query = foxProDatabase.query("select * from clienti.dbf");

        query.forEach(System.out::println);

    }

}
