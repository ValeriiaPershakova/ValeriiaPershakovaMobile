package pages.nativeApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBasePage;

/**
 * Class describes and initializes elements on Login page
 */
public class LoginPage extends AbstractBasePage {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Register new account']")
    private WebElement registerBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sign In']")
    private WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Username or email']/following-sibling::XCUIElementTypeTextField")
    private WebElement emailTextField;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Password']/following-sibling::XCUIElementTypeSecureTextField")
    private WebElement pswdTextField;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView\n")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeNavigationBar/XCUIElementTypeOther[@label='EPAM Test App']")
    private WebElement pageTitle;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void register() {
        registerBtn.click();
    }

    public void setEmail(String email) {
        emailTextField.sendKeys(email);
    }

    public void setPassword(String password) {
        pswdTextField.sendKeys(password);
    }

    public void signIn() {
        signInBtn.click();
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public WebElement getRegisterBtn() {
        return registerBtn;
    }
}
