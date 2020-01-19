package pages.nativeApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import pages.AbstractBasePage;

/**
 * Class describes and initializes elements on BudgetActivity page
 */
public class BudgetActivityPage extends AbstractBasePage {
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeNavigationBar/XCUIElementTypeOther[@label='Budget']")
    private WebElement pageTitle;

    public BudgetActivityPage(AppiumDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
}
