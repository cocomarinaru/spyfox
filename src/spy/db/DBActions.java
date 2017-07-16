package spy.db;

import java.util.List;

/**
 * Created by cocos on 7/16/2017.
 */
public interface DBActions {

    List<Row> query(String query);


}
