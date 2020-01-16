package scenarios.webtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import scenarios.Hooks;

import java.util.List;

@Test(groups = "web")
public class WebSimpleTest extends Hooks {

    @Test(description = "Simple google search")
    public void googleTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlMatches(SUT + ".*\\/.*"));
        driverWait().until(ExpectedConditions.titleContains(HOMEPAGETITLE));


        driver().findElement(By.xpath("//input[@name='q']")).sendKeys(QUERY);
        driver().findElement(By.xpath("//button[@class='Tg7LZd']")).click();

        driverWait().until(ExpectedConditions.urlContains(QUERY));

        By result = By.xpath("//div[@class='srg']//*[contains(text(),'" + QUERY + "')]");

        List<WebElement> results = driver().findElements(result);
        Assert.assertFalse(results.isEmpty(), "it is impossible to find search results");

        System.out.println("Search is done");


    }


}
