package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * DeviceProperties class purpose is to read device properties from file
 */
public class DeviceProperties {
    private Properties currentProps = new Properties();

    private Properties getCurrentProps() throws IOException {
        FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/target/test-classes/device.properties");
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    public String getProp(String propKey) throws IOException {
        if (!currentProps.containsKey(propKey)) {
            currentProps = getCurrentProps();
        }
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}

