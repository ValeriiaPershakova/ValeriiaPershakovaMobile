package pages.web;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBasePage;

/**
 * Class describes and initializes elements on Search page
 */
public class SearchPage extends AbstractBasePage {
    @FindBy(name = "q")
    private WebElement queryField;
    @FindBy(xpath = "//button[@class='Tg7LZd']")
    private WebElement searchBtn;

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public void sendQuery(String query) {
        queryField.sendKeys(query);
        searchBtn.click();
    }
}
