package scenarios.webtests;

import Utils.Screen;
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
        driverWait().until(ExpectedConditions.titleContains("Google"));


        driver().findElement(By.xpath("//input[@name='q']")).sendKeys(QUERY);
        driver().findElement(By.xpath("//button[@class='Tg7LZd']")).click();

        driverWait().until(ExpectedConditions.urlContains(QUERY));

        By result = By.xpath("//div[@class='srg']//*[contains(text(),'" + QUERY + "')]");
        boolean isFoundElement = driver().findElements(result).size() > 0;
        try {
            while (!isFoundElement) {
                Screen.swipeVertical(driver(), 0.9, 0.1, 0.5, 2000);
                isFoundElement = driver().findElements(result).size() > 0;
            }
        } catch (Exception e) {
            System.out.println("error occured while scrolling to find corresponding results");
            e.printStackTrace();
        }

        List<WebElement> results = driver().findElements(By.xpath("//div[@class='srg']//*[contains(text(),'" + QUERY + "')]"));
        Assert.assertFalse(results.isEmpty(), "it is impossible to find search results");
        System.out.println("Search is done");


    }


}
