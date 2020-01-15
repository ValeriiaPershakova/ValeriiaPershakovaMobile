package setup;

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
