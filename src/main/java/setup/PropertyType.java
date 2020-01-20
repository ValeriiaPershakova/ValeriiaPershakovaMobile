package setup;

/**
 * Contains path to appropriate properties files in accordance with test type
 */
public enum PropertyType {
    WEB("/target/test-classes/webtest.properties"),
    NATIVE_IOS("/target/test-classes/iOSnativetest.properties"),
    NATIVE_ANDROID("/target/test-classes/androidnativetest.properties");

    private String filePath;

    PropertyType(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
