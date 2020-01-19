package setup;

import api.MobileCloudApi;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
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
    //common
    protected static String TEST_PLATFORM;
    protected static String UDID;
    protected static String DRIVER;
    protected static String TOKEN;
    protected static String PROJECT_NAME = "EPM-TSTF";

    //native
    protected static String AUT; //(mobile) app under testing
    protected static String APP_PACKAGE;
    protected static String APP_ACTIVITY;
    protected static String EMAIL;
    protected static String USERNAME;
    protected static String PASSWORD;

    //web
    protected static String SUT; // site under testing
    protected static String QUERY;
    protected static String HOMEPAGETITLE;

    public static String getDriver() {
        return DRIVER;
    }

    /**
     * Initialize properties from appropriate file
     *
     * @throws IOException
     */
    private void initProps() throws IOException {
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        UDID = getProp("udid");
        //TOKEN = getProp(PropertyType.CREDENTIALS, "token");

        AUT = getProp("aut");
        APP_PACKAGE = getProp("package");
        APP_ACTIVITY = getProp("activity");

        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "https://" + t_sut;
        QUERY = getProp("query");
        HOMEPAGETITLE = getProp("homePageTitle");
        EMAIL = getProp("email");
        USERNAME = getProp("username");
        PASSWORD = getProp("password");

    }

    /**
     * Provide instance of AppiumDriver object
     *
     * @return AppiumDriver
     */
    protected AppiumDriver driver() {
        if (driver == null) {
            throw new RuntimeException("Driver hasn't been initialized");
        }
        return driver;
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform, test type and application
     *
     * @throws Exception
     */
    protected void prepareDriver() throws Exception {
        initProps();
        capabilities = new DesiredCapabilities();
        String browserName;

        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); // default Android emulator
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);
        if (UDID != "") {
            capabilities.setCapability(MobileCapabilityType.UDID, UDID);
        }

        // Setup type of application: native, web
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            switch (TEST_PLATFORM) {
                case "Android": {
                    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                    capabilities.setCapability("appPackage", APP_PACKAGE);
                    capabilities.setCapability("appActivity", APP_ACTIVITY);
                    break;
                }
                case "iOS": {
                    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                    break;
                }


            }
            UDID = MobileCloudApi.takeDevice(capabilities);
            MobileCloudApi.installAppToDevice(app, UDID);
            capabilities.setCapability(MobileCapabilityType.UDID, UDID);
            capabilities.setCapability("keepDevice", true);

        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            // this is required to prevent exception "Cannot call non W3C standard" for <77 chromedriver version
            capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
//            //capabilities.setCapability("chromedriverExecutableDir", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedrivers");
//            capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedrivers\\chromedriver_win32_v2.17.exe");

        } else {
            throw new Exception("Unclear type of mobile app");
        }


        String url = TokenInstance.insertToken(DRIVER);
        System.out.println(url);
        // Init driver for local Appium server with capabilities have been set
        if (driver == null) {
//            switch (TEST_PLATFORM) {
//                case "Android": {
//                    driver = new AndroidDriver(new URL(url), capabilities);
//                    break;
//                }
//                case "iOS": {
//                    driver = new IOSDriver(new URL(url), capabilities);
//                    break;
//                }
//            }
            driver = new AppiumDriver(new URL(url), capabilities);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver, 10);
        }
    }

    /**
     * Provide instance of WebDriverWait object
     *
     * @return WebDriverWait
     */
    protected WebDriverWait driverWait() {
        return waitSingle;
    }
}


