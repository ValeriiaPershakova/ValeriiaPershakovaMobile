package scenarios.nativetests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.nativeApp.BudgetActivityPage;
import pages.nativeApp.LoginPage;
import pages.nativeApp.RegistrationPage;
import scenarios.hooks.Hooks;

import static java.lang.String.format;

/**
 * Class contains test scripts for native app
 */
@Test(groups = {"android", "ios", "native"})
public class NativeSimpleTest extends Hooks {

    @Test(description = "This test register new account in Epam Test App and sign in with new credentials")
    public void epamTestAppTest() {
        SoftAssert softAssert = new SoftAssert();
        //touchActions are performed to hide keyboard and activate page. Method driver.hideKeyboard() is unstable on iOS-devices and can't be used
        TouchAction touchAction = new TouchAction(driver());

        //start (login) page
        LoginPage loginPage = new LoginPage(driver());
        driverWait().until(ExpectedConditions.visibilityOf(loginPage.getRegisterBtn()));
        loginPage.register();

        //registration page
        RegistrationPage registrationPage = new RegistrationPage(driver());
        driverWait().until(ExpectedConditions.visibilityOf(registrationPage.getPageTitle()));
        softAssert.assertEquals(registrationPage.getPageTitleText(), "Registration",
                format("Inexpected title of Registration page: %s", registrationPage.getPageTitleText()));
        registrationPage.setEmail(EMAIL);
        registrationPage.setUsername(USERNAME);
        registrationPage.setPassword(PASSWORD);
        registrationPage.confirmPassword(PASSWORD);
        registrationPage.setAgreementCheckbox();
        /* Following assertion can't be perform both on Android and iOS devices because of the different attribute sets for Checkbox element
        softAssert.assertTrue(registrationPage.getAgreementCheckboxState().matches("(1)|(true)"),
                "Checkbox isn't selected");
        */
        touchAction.tap(PointOption.point(150, 150)).perform();
        registrationPage.register();


        // start (login) page
        driverWait().until(ExpectedConditions.visibilityOf(loginPage.getRegisterBtn()));
        softAssert.assertEquals(loginPage.getPageTitleText(), "EPAM Test App",
                format("Inexpected title of Login page: %s", loginPage.getPageTitleText()));
        loginPage.setEmail(EMAIL);
        loginPage.setPassword(PASSWORD);

        touchAction.tap(PointOption.point(150, 150)).perform();

        loginPage.signIn();

        //budgetActivity page
        BudgetActivityPage budgetActivityPage = new BudgetActivityPage(driver());
        driverWait().until(ExpectedConditions.visibilityOf(budgetActivityPage.getPageTitle()));

        softAssert.assertEquals(budgetActivityPage.getPageTitleText(), "BudgetActivity",
                format("Inexpected title of BudgetActivity page: %s", budgetActivityPage.getPageTitleText()));

        softAssert.assertAll();
        System.out.println("EPAMTestApp test done");
    }

}


