package pages.web;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractBasePage;

import java.util.List;

/**
 * Class describes and initializes elements on Search results page
 */

public class SearchResultsPage extends AbstractBasePage {
    @FindBy(xpath = "//div[@class='srg']/div")
    private List<WebElement> results;

    public SearchResultsPage(AppiumDriver driver) {
        super(driver);
    }

    public List<WebElement> getResults() {
        return results;
    }
}
