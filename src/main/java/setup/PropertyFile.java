package setup;

/**
 * Contains path to appropriate properties files in accordance with test type
 */
public enum PropertyFile {
    WEB("/webtest.properties"),
    NATIVE("/nativetest.properties");

    private String filePath;

    PropertyFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
