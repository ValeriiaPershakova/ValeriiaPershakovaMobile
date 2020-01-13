package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FirstSimpleTest extends DriverSetup {
    private String email = "test@epam.com";
    private String username = "username";
    private String password = "1234pass";
    private static int count = 0;

    @BeforeClass
    /**
     * Prepare driver to run test(s)
     */
    public void setUp() throws Exception {
        prepareAndroidNative();
        //generateTestData();
    }


    @Test
    /**
     * This simple test just click on button 'Add contact'
     */
    public void SimplestTest() {
        SoftAssert softAssert = new SoftAssert();

        //start (login) page
        String appPackageName = "platkovsky.alexey.epamtestapp:id/";
        By registerBtn = By.id(appPackageName + "register_button");
        driver.findElement(registerBtn).click();
// /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView
// /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.CheckedTextView
        //registration page
        By title = By.id(appPackageName + "action_bar");
        WebElement titleElement = driver.findElement(title);
        //String titleText = titleElement.findElement(By.xpath("/android.widget.TextView")).getText();
        //String titleText = titleElement.getText();
        String titleText = driver.findElement(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")).getText();
        softAssert.assertEquals(titleText, "Registration");

        By registrationEmail = By.id(appPackageName + "registration_email");
        By usernameTextField = By.id(appPackageName + "registration_username");
        By registrationPswd = By.id(appPackageName + "registration_password");
        By confirmPswd = By.id(appPackageName + "registration_confirm_password");

        driver.findElement(registrationEmail).sendKeys(email);
        driver.findElement(usernameTextField).sendKeys(username);
        driver.findElement(registrationPswd).sendKeys(password);
        driver.findElement(confirmPswd).sendKeys(password);

        //By registrationForm = By.id(appPackageName+"email_registration_form");
        WebElement agreementCheckbox = driver.findElement(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.CheckedTextView"));
        agreementCheckbox.click();
        softAssert.assertTrue(agreementCheckbox.isSelected());

        By registerNewAccountBtn = By.id(appPackageName + "register_new_account_button");
        driver.findElement(registerNewAccountBtn).click();
        // start (login) page
        By loginEmail = By.id(appPackageName + "login_email");
        By loginPwd = By.id(appPackageName + "login_pwd");

        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPwd).sendKeys(password);
        By signInBtn = By.id(appPackageName + "email_sign_in_button");
        driver.findElement(signInBtn).click();

        //budgetActivity page
        titleText = driver.findElement(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")).getText();
        softAssert.assertEquals(titleText, "BudgetActivity");

        softAssert.assertAll();
        System.out.println("Simplest Appium test done");
    }

    private void generateTestData() {
        email = "test" + count + "@mail.com";
        username = "username" + count;
        count++;
    }

    @AfterClass
    /**
     * Close driver on all tests completion
     */
    public void tearDown() throws Exception {
        driver.quit();
    }
}


