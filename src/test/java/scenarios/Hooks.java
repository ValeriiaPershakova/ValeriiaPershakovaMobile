package scenarios;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import setup.Driver;
import setup.PropertyFile;


public class Hooks extends Driver {

    @BeforeGroups(description = "Prepare driver to run webtest(s)",
            groups = "web")
    public void setUpWeb() throws Exception {
        prepareDriver(PropertyFile.WEB);
        System.out.println("Driver prepared");
    }

    @BeforeGroups(description = "Prepare driver to run nativtest(s)",
            groups = "native")
    public void setUpNative() throws Exception {
        prepareDriver(PropertyFile.NATIVE);
        System.out.println("Driver prepared");
    }

    @AfterGroups(description = "Close driver on all tests completion",
            groups = {"native", "web"})
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }
}
