package spy.logic;

import spy.Config;
import spy.db.FoxProDatabase;
import spy.db.Row;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class FileGenerator {

    public void generate(String outputDirectory, LocalDate startDate, LocalDate stopDate) {

        System.out.println(outputDirectory);
        System.out.println(startDate);
        System.out.println(stopDate);

        String databasePath = Config.getDatabasePath();
        FoxProDatabase foxProDatabase = new FoxProDatabase(databasePath);

        String startDateString = convertDate(startDate);
        String stopDateString = convertDate(stopDate);
        String query = String.format("SELECT * from totcom.dbf where datadoc >= DATE(%s) and datadoc <= DATE(%s)", startDateString, stopDateString);
        System.out.println("Executing query: " + query);
        List<Row> result = foxProDatabase.query(query);

        System.out.println(result.size());

    }

    private String convertDate(LocalDate date) {
        String startYear = Objects.toString(date.getYear());
        String startMonth = Objects.toString(date.getMonthValue());
        String startDay = Objects.toString(date.getDayOfMonth());
        return String.join(",", startYear, startMonth, startDay);
    }
}
