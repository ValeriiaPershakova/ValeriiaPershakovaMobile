package setup;

/**
 * Contains path to appropriate properties files in accordance with test type
 */
public enum PropertyType {
    WEB("/target/test-classes/webtest.properties"),
    NATIVE("/target/test-classes/nativetest.properties");

    private String filePath;

    PropertyType(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
