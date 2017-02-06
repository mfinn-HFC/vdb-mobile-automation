package ios.search;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import screens.ios.SearchResultScreen;
import screens.ios.SearchScreen;

/**
 * Created by matt-hfc on 1/17/17.
 */
public class SearchByShapeTest extends iOSBaseTest {

    private int numberOfResultsToCheck = 2; // Index of 0, means 3 total

    @Test(dataProvider = "ios")
    public void searchByShapeTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        loginScreen.acceptNotificationsButton();

        SearchScreen searchScreen = loginScreen.defaultLogin();
        searchScreen.getShapeWheelButton().click();
        searchScreen.getHeartShapeSelectionButton().click();
        SearchResultScreen searchResultScreen = searchScreen.tapSearchButton();

        // Wait for results to load
        searchResultScreen.waitForSearchResultsToLoad();

        for (int i = 0; i < numberOfResultsToCheck; i++)
        {
            Assert.assertTrue(searchResultScreen.getResultListChildren().get(i).getText().contains("HS"));
        }
    }
}
