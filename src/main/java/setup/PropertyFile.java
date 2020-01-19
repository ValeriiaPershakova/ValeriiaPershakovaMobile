package setup;

public enum PropertyFile {
    INSTANCE;

    private String propFilePath;


    public static void setInstance(PropertyType propertyType) {
        INSTANCE.propFilePath = propertyType.getFilePath();
    }

    public static String getPropertiesPath() {
        return INSTANCE.propFilePath;
    }
}
