package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * TestProperties class purpose is to read properties from file
 */
public class TestProperties {
    private Properties currentProps = new Properties();

    private Properties getCurrentProps() throws IOException {
        FileInputStream in = new FileInputStream(System.getProperty("user.dir") + PropertyFile.getPropertiesPath());
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey) throws IOException {
        if (!currentProps.containsKey(propKey)) {
            currentProps = getCurrentProps();
        }
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}

