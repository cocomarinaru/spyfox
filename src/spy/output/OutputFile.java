package spy.output;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputFile {

    private String fileName;
    private Set<Category> categories;
    private List<Property> rootProperties;

    public OutputFile(String path) {
        this.fileName = path;
        categories = new HashSet<>();
        rootProperties = new ArrayList<>();
    }

    public void addCategory(Category category) {
        if (category != null) {
            categories.add(category);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public List<Property> getRootProperties() {
        return rootProperties;
    }

    @Override
    public String toString() {
        String result = "File " + fileName + "\n\t";
        result += rootProperties.stream()
                .map(Property::toString)
                .collect(Collectors.joining("\n\t"));

        result += categories.stream()
                .map(Category::toString)
                .collect(Collectors.joining("\n\t"));

        return result;
    }
}
