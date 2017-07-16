package spy.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputFileReader {

    private static final Pattern CATEGORY_TITLE_REGEX = Pattern.compile("\\[(.+?)]");
    private static final Pattern PROPERTY_REGEX = Pattern.compile("(.+?)=(.*?)");

    public static InputFile readInputFile(String path) {

        InputFile inputFile = new InputFile(path);

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            InputCategory currentCategory = null;
            for (String line : lines) {

                currentCategory = readCategory(inputFile, currentCategory, line);
                readProperty(inputFile, currentCategory, line);
            }
        } catch (IOException e) {
            System.out.println("ERROR: Reading " + path + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return inputFile;
    }

    private static void readProperty(InputFile inputFile, InputCategory currentCategory,
        String line) {
        Optional<InputProperty> property = getPropertyFromLine(line);
        if (property.isPresent()) {
            if (currentCategory == null) {
                inputFile.getRootProperties().add(property.get());
            } else {
                currentCategory.getProperties().add(property.get());
            }
        }
    }

    private static InputCategory readCategory(InputFile inputFile,
        InputCategory currentCategory, String line) {

        Optional<String> categoryTitle = getCategoryTitleFromLine(line);

        if (categoryTitle.isPresent()) {
            inputFile.addCategory(currentCategory);
            currentCategory = new InputCategory(categoryTitle.get());
        }
        return currentCategory;
    }

    private static Optional<InputProperty> getPropertyFromLine(String line) {

        Matcher matcher = PROPERTY_REGEX.matcher(line);

        if (matcher.matches()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            return Optional.of(new InputProperty(key, value));
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
