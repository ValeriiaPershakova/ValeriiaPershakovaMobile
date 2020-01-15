package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Initialize a driver with test properties
 */
public class Driver extends TestProperties {
    private static AppiumDriver driver = null;
    private static WebDriverWait waitSingle = null;
    protected DesiredCapabilities capabilities;

    // Properties to be read
    protected static String AUT; //(mobile) app under testing
    protected static String SUT; // site under testing
    protected static String TEST_PLATFORM;
    protected static String DRIVER;
    protected static String CHROMEDRIVER;
    protected static String QUERY;


    // Constructor initializes properties on driver creation
    protected void initProps(PropertyFile propertyFile) throws IOException {
        TEST_PLATFORM = getProp(propertyFile, "platform");
        DRIVER = getProp(propertyFile, "driver");
        AUT = getProp(propertyFile, "aut");

        String t_sut = getProp(propertyFile, "sut");
        SUT = t_sut == null ? null : "https://" + t_sut;
        CHROMEDRIVER = SUT == null ? null : getProp(propertyFile, "chromedriver");
        QUERY = getProp(propertyFile, "query");
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws Exception
     */
    protected void prepareDriver(PropertyFile propertyFile) throws Exception {
        initProps(propertyFile);
        capabilities = new DesiredCapabilities();
        String browserName;

        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); // default Android emulator
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            if (CHROMEDRIVER != null) {
                capabilities.setCapability("chromedriverExecutable", CHROMEDRIVER);
            }
        } else {
            throw new Exception("Unclear type of mobile app");
        }


        // Init driver for local Appium server with capabilities have been set
        // BUILDER AndroidDriver (depends on platform)
        if (driver == null) {
            switch (TEST_PLATFORM) {
                case "Android": {
                    driver = new AppiumDriver(new URL(DRIVER), capabilities);
                    break;
                }
                case "iOS": {
                    driver = new IOSDriver(new URL(DRIVER), capabilities);
                    break;
                }
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver, 10);
        }
    }

    protected AppiumDriver driver() throws Exception {
        if (driver == null) {
            throw new RuntimeException("Driver hasn't been initialized");
            //prepareDriver();
        }
        return driver;
    }

    protected WebDriverWait driverWait() throws Exception {
        return waitSingle;
    }
}


