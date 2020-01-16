package scenarios.webtests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.web.SearchPage;
import pages.web.SearchResultsPage;
import scenarios.hooks.Hooks;

/**
 * Class contains test scripts for web
 */
@Test(groups = "web")
public class WebSimpleTest extends Hooks {

    @Test(description = "Simple google search")
    public void googleTest() throws Exception {
        //open site
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlMatches(SUT + ".*\\/.*"));
        Assert.assertEquals(driver().getTitle(), HOMEPAGETITLE,
                String.format("Inexpected Home page title: %s", driver().getTitle()));
        //send query
        SearchPage searchPage = new SearchPage(driver());
        searchPage.sendQuery(QUERY);

        //get results
        driverWait().until(ExpectedConditions.urlContains(QUERY));
        SearchResultsPage resultsPage = new SearchResultsPage(driver());
        Assert.assertFalse(resultsPage.getResults().isEmpty(), "it is impossible to find search results");

        System.out.println("Search is done");


    }


}
