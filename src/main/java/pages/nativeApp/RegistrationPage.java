package pages.nativeApp;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBasePage;

/**
 * Class describes and initializes elements on Registration page
 */
public class RegistrationPage extends AbstractBasePage {
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    private WebElement pageTitle;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    private WebElement registrationEmail;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    private WebElement usernameTextField;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    private WebElement registrationPswd;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    private WebElement confirmPswd;
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.CheckedTextView")
    private WebElement agreementCheckbox;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
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

    public WebElement getAgreementCheckbox() {
        return agreementCheckbox;
    }

    public void register() {
        registerNewAccountBtn.click();
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public WebElement getRegistrationEmail() {
        return registrationEmail;
    }
}
