package scenarios.nativetests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import scenarios.Hooks;

@Test(groups = "native")
public class NativeSimpleTest extends Hooks {
    private String email = "test@epam.com";
    private String username = "username";
    private String password = "1234pass";


    @Test
    /**
     * This simple test just click on button 'Add contact'
     */
    public void epamTestAppTest() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        //start (login) page
        String appPackageName = "platkovsky.alexey.epamtestapp:id/";
        By registerBtn = By.id(appPackageName + "register_button");
        driver().findElement(registerBtn).click();

        //registration page
        By title = By.id(appPackageName + "action_bar");
        By registrationTitle = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView");
        driverWait().until(ExpectedConditions.presenceOfElementLocated(registrationTitle));
        String titleText = driver().findElement(registrationTitle).getText();
        softAssert.assertEquals(titleText, "Registration");

        By registrationEmail = By.id(appPackageName + "registration_email");
        By usernameTextField = By.id(appPackageName + "registration_username");
        By registrationPswd = By.id(appPackageName + "registration_password");
        By confirmPswd = By.id(appPackageName + "registration_confirm_password");

        driver().findElement(registrationEmail).sendKeys(email);
        driver().findElement(usernameTextField).sendKeys(username);
        driver().findElement(registrationPswd).sendKeys(password);
        driver().findElement(confirmPswd).sendKeys(password);


        WebElement agreementCheckbox = driver().findElement(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.CheckedTextView"));
        agreementCheckbox.click();
        softAssert.assertTrue(agreementCheckbox.isSelected(), "Checkbox isn't selected");

        By registerNewAccountBtn = By.id(appPackageName + "register_new_account_button");
        driver().findElement(registerNewAccountBtn).click();

        // start (login) page
        By startPageTitle = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView\n");
        driverWait().until(ExpectedConditions.presenceOfElementLocated(startPageTitle));
        titleText = driver().findElement(startPageTitle).getText();
        softAssert.assertEquals(titleText, "EPAM Test App");

        By loginEmail = By.id(appPackageName + "login_email");
        By loginPwd = By.id(appPackageName + "login_pwd");

        driver().findElement(loginEmail).sendKeys(email);
        driver().findElement(loginPwd).sendKeys(password);
        By signInBtn = By.id(appPackageName + "email_sign_in_button");
        driver().findElement(signInBtn).click();

        //budgetActivity page
        By budgetActivityTitle = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView");
        driverWait().until(ExpectedConditions.presenceOfElementLocated(budgetActivityTitle));
        titleText = driver().findElement(budgetActivityTitle).getText();
        softAssert.assertEquals(titleText, "BudgetActivity");

        softAssert.assertAll();
        System.out.println("EPAMTestApp test done");
    }

}


