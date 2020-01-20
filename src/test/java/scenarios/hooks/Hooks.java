package scenarios.hooks;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import setup.Driver;
import setup.PropertyFile;
import setup.PropertyType;

/**
 * Hooks class purpose is to load and set driver's properties according to the test type (native, web)
 */
public class Hooks extends Driver {

    @BeforeGroups(description = "Prepare driver to run webtest(s)",
            groups = "web")
    public void setUpWeb() throws Exception {
        PropertyFile.setInstance(PropertyType.WEB);
        prepareDriver();
        System.out.println("Driver prepared");
    }

    @BeforeGroups(description = "Prepare driver to run nativtest(s)",
            groups = {"android"})
    public void setUpNativeAndroid() throws Exception {
        PropertyFile.setInstance(PropertyType.NATIVE_ANDROID);
        prepareDriver();
        System.out.println("Driver prepared");
    }

    @BeforeGroups(description = "Prepare driver to run nativtest(s)",
            groups = {"ios"})
    public void setUpNativeIos() throws Exception {
        PropertyFile.setInstance(PropertyType.NATIVE_IOS);
        prepareDriver();
        System.out.println("Driver prepared");
    }

    @AfterGroups(description = "Close driver on all tests completion",
            groups = {"native", "web"})
    public void tearDown() {
        driver().quit();
        System.out.println("Driver closed");
    }
}
