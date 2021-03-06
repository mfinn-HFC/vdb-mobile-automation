package ios.search;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.SearchResultScreen;
import screens.ios.SearchScreen;

/**
 * Created by matt-hfc on 1/17/17.
 */
public class BudgetSearchTest extends iOSBaseTest {

    @Test(dataProvider = "ios")
    public void budgetSearchTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());

        loginScreen.acceptNotificationsButton();
        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        SearchScreen searchScreen = new SearchScreen(driver);

        searchScreen.getFromValueInput().sendKeys("100");
        searchScreen.getToValueInput().sendKeys("100");
        searchScreen.getToText().click();
        searchScreen.tapSearchButton();

        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);
        Assert.assertTrue(searchResultScreen.getFavoriteButtonGridView().size() >= 1);
    }
}
