package spy.db;

import spy.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FoxProService {

    private static FoxProService instance;
    private Map<String, List<Row>> databses;

    private FoxProService() {

        readAllDatabases();
    }

    public static FoxProService getInstance() {

        if (instance == null) {
            instance = new FoxProService();
        }

        return instance;

    }

    private void readAllDatabases() {

        this.databses = new HashMap<>();

        List<String> tableNames = new ArrayList<String>();

        String databasePath = Config.getDatabasePath();
        FoxProDatabase foxProDatabase = new FoxProDatabase(databasePath);

        try {
            tableNames = getAllTableNames(databasePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String table : tableNames) {
            String query = "SELECT * FROM " + table;
            List<Row> rows = foxProDatabase.query(query);
            databses.put(table, rows);
        }

        System.out.println();

    }

    private List<String> getAllTableNames(String databasePath) throws IOException {


        List<String> tableNames = Files.list(Paths.get(databasePath))
                .filter(Files::isRegularFile)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(this::isDBF)
                .collect(Collectors.toList());

        return tableNames;
    }


    private boolean isDBF(String path) {
        return path.toLowerCase().endsWith(".dbf");
    }

}
