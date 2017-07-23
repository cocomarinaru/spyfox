package spy.db;

import spy.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoxProService {

    private static FoxProService instance;

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

        List<String> tablesName = new ArrayList<String>();

        try {
            String databasePath = Config.getDatabasePath();
            FoxProDatabase foxProDatabase = new FoxProDatabase(databasePath);
            tablesName = getAllTableNames(databasePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tablesName.forEach(System.out::println);

    }

    private List<String> getAllTableNames(String databasePath) throws IOException {


        List<String> tableNames = Files.list(Paths.get(databasePath))
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .filter(this::isDBF)
                .map(p -> this.removePrefix(p, databasePath))
                .collect(Collectors.toList());

        return tableNames;
    }

    private String removePrefix(String path
            , String prefix) {

        return path.replace(prefix, "");

    }

    private boolean isDBF(String path) {
        return path.toLowerCase().endsWith(".dbf");
    }

}
