package android.search;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.android.SearchResultScreen;
import screens.android.SearchScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class BudgetSearchTest extends AndroidBaseTest {

    @Test(dataProvider = "drivers")
    public void budgetSearchTest(AndroidDriver driver) throws InterruptedException {
        setUp(driver);
        waitForBugReportPromptToClose();

        loginScreen.login();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));
        SearchScreen searchScreen = new SearchScreen(driver);

        searchScreen.getTapToTypeTop().click();
        wait.until(ExpectedConditions.visibilityOf(searchScreen.getEditFromPriceField()));
        searchScreen.getEditFromPriceField().sendKeys("100");

        searchScreen.getTapToTypeBottom().click();
        wait.until(ExpectedConditions.visibilityOf(searchScreen.getEditToPriceField()));
        searchScreen.getEditToPriceField().sendKeys("100");

        // Keycode for 'Enter' - hideKeyboard was crashing the app in this screen
        driver.pressKeyCode(66);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(searchScreen.getSearchButton()));
        searchScreen.getSearchButton().click();
        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);

        wait.until(ExpectedConditions.visibilityOf(searchResultScreen.getRefineSearchHeader()));
        Assert.assertTrue(searchResultScreen.getSearchResultImages().size() >= 1);
        Assert.assertTrue(searchResultScreen.getFavoriteButtons().size() >= 1);
    }
}