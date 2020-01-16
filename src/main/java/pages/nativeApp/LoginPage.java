package pages.nativeApp;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBasePage;

/**
 * Class describes and initializes elements on Login page
 */
public class LoginPage extends AbstractBasePage {
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    private WebElement registerBtn;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    private WebElement signInBtn;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    private WebElement emailTextField;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    private WebElement pswdTextField;
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView\n")
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
