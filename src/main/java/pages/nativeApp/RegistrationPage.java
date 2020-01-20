package pages.nativeApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import pages.AbstractBasePage;

/**
 * Class describes and initializes elements on Registration page
 */
public class RegistrationPage extends AbstractBasePage {
    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[1]/android.view.View/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='Registration']")
    private WebElement pageTitle;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Email']/following-sibling::XCUIElementTypeTextField")
    private WebElement registrationEmail;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Username']/following-sibling::XCUIElementTypeTextField")
    private WebElement usernameTextField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Password']/following-sibling::XCUIElementTypeSecureTextField")
    private WebElement registrationPswd;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Confirm password']/following-sibling::XCUIElementTypeSecureTextField")
    private WebElement confirmPswd;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.CheckedTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch[@name='I read agreaments and agree wit it']")
    private WebElement agreementCheckbox;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Register new account']")
    private WebElement registerNewAccountBtn;

    public RegistrationPage(AppiumDriver driver) {
        super(driver);
    }

    public void setEmail(String email) {
        registrationEmail.sendKeys(email);
    }

    public void setUsername(String username) {
        usernameTextField.sendKeys(username);
    }

    public void setPassword(String password) {
        registrationPswd.sendKeys(password);
    }

    public void confirmPassword(String password) {
        confirmPswd.sendKeys(password);
    }

    public void setAgreementCheckbox() {
        agreementCheckbox.click();
    }

    public void register() {
        registerNewAccountBtn.click();
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

}
