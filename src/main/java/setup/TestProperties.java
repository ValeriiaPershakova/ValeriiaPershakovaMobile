package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * TestProperties class purpose is to read properties from file
 */
public class TestProperties {
    private Properties currentProps = new Properties();

    private Properties getCurrentProps(PropertyFile propertyFile) throws IOException {
        FileInputStream in = new FileInputStream(System.getProperty("user.dir") + propertyFile.getFilePath());
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(PropertyFile filePath, String propKey) throws IOException {
        if (!currentProps.containsKey(propKey)) currentProps = getCurrentProps(filePath);
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}

