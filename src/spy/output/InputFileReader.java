package spy.output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputFileReader {
    private static final Logger LOGGER = LogManager.getLogger(InputFileReader.class);

    private static final Pattern CATEGORY_TITLE_REGEX = Pattern.compile("\\[(.+?)]");
    private static final Pattern PROPERTY_REGEX = Pattern.compile("(.+?)=(.*?)");

    public static OutputFile readInputFile(String path) {

        OutputFile outputFile = new OutputFile(path);

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            Category currentCategory = null;
            for (String line : lines) {

                currentCategory = readCategory(outputFile, currentCategory, line);
                readProperty(outputFile, currentCategory, line);
            }
        } catch (IOException e) {
            LOGGER.error("ERRROR: " + e.getMessage(), e);
            return null;
        }
        return outputFile;
    }

    private static void readProperty(OutputFile outputFile, Category currentCategory,
                                     String line) {
        Optional<Property> property = getPropertyFromLine(line);
        if (property.isPresent()) {
            if (currentCategory == null) {
                outputFile.getRootProperties().add(property.get());
            } else {
                currentCategory.getProperties().add(property.get());
            }
        }
    }

    private static Category readCategory(OutputFile outputFile,
                                         Category currentCategory, String line) {

        Optional<String> categoryTitle = getCategoryTitleFromLine(line);

        if (categoryTitle.isPresent()) {
            outputFile.addCategory(currentCategory);
            currentCategory = new Category(categoryTitle.get());
        }
        return currentCategory;
    }

    private static Optional<Property> getPropertyFromLine(String line) {

        Matcher matcher = PROPERTY_REGEX.matcher(line);

        if (matcher.matches()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            return Optional.of(new Property(key, value));
        }
        return Optional.empty();
    }

    private static Optional<String> getCategoryTitleFromLine(String line) {

        Matcher matcher = CATEGORY_TITLE_REGEX.matcher(line);

        if (matcher.matches()) {
            return Optional.ofNullable(matcher.group(1));
        }
        return Optional.empty();
    }
}
