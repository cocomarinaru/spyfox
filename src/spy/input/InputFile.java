package spy.input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputFile {

    private String fileName;
    private Set<InputCategory> categories;
    private List<InputProperty> rootProperties;

    public InputFile(String path) {
        this.fileName = path;
        categories = new HashSet<>();
        rootProperties = new ArrayList<>();
    }

    public void addCategory(InputCategory category) {
        if (category != null) {
            categories.add(category);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public Set<InputCategory> getCategories() {
        return categories;
    }

    public List<InputProperty> getRootProperties() {
        return rootProperties;
    }

    @Override
    public String toString() {
        String result = "File " + fileName + "\n\t";
        result += rootProperties.stream()
                .map(InputProperty::toString)
                .collect(Collectors.joining("\n\t"));

        result += categories.stream()
                .map(InputCategory::toString)
                .collect(Collectors.joining("\n\t"));

        return result;
    }
}
