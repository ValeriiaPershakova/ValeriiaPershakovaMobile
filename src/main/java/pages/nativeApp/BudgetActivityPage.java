package pages.nativeApp;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBasePage;

/**
 * Class describes and initializes elements on BudgetActivity page
 */
public class BudgetActivityPage extends AbstractBasePage {
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView")
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
