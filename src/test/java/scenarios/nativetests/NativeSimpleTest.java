package scenarios.nativetests;

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
@Test(groups = "native")
public class NativeSimpleTest extends Hooks {

    @Test(description = "This test register new account in Epam Test App and sign in with new credentials")
    public void epamTestAppTest() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        //start (login) page
        LoginPage loginPage = new LoginPage(driver());
        driverWait().until(ExpectedConditions.visibilityOf(loginPage.getRegisterBtn()));
        loginPage.register();

        //registration page
        RegistrationPage registrationPage = new RegistrationPage(driver());
        driverWait().until(ExpectedConditions.visibilityOf(registrationPage.getRegistrationEmail()));
        softAssert.assertEquals(registrationPage.getPageTitleText(), "Registration",
                format("Inexpected title of Registration page: %s", registrationPage.getPageTitleText()));
        registrationPage.setEmail(EMAIL);
        registrationPage.setUsername(USERNAME);
        registrationPage.setPassword(PASSWORD);
        registrationPage.confirmPassword(PASSWORD);
        registrationPage.setAgreementCheckbox();
        softAssert.assertTrue(registrationPage.getAgreementCheckbox().isSelected(),
                "Checkbox isn't selected");
        registrationPage.register();

        // start (login) page
        driverWait().until(ExpectedConditions.visibilityOf(loginPage.getRegisterBtn()));
        softAssert.assertEquals(loginPage.getPageTitleText(), "EPAM Test App",
                format("Inexpected title of Login page: %s", loginPage.getPageTitleText()));
        loginPage.setEmail(EMAIL);
        loginPage.setPassword(PASSWORD);
        loginPage.signIn();

        //budgetActivity page
        //driverWait().withTimeout(Duration.ofSeconds(10));

        BudgetActivityPage budgetActivityPage = new BudgetActivityPage(driver());
        driverWait().until(ExpectedConditions.visibilityOf(budgetActivityPage.getPageTitle()));

        softAssert.assertEquals(budgetActivityPage.getPageTitleText(), "BudgetActivity",
                format("Inexpected title of BudgetActivity page: %s", budgetActivityPage.getPageTitleText()));

        softAssert.assertAll();
        System.out.println("EPAMTestApp test done");
    }

}


