package android.search;

import base.AndroidBaseTest;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import screens.android.SearchResultScreen;
import screens.android.SearchScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class BudgetSearchTest extends AndroidBaseTest {

    public BudgetSearchTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void budgetSearchTest() throws InterruptedException {
        waitForBugReportPromptToClose();

        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));
        SearchScreen searchScreen = new SearchScreen(driver);

        searchScreen.getTapToTypeTop().click();
        wait.until(ExpectedConditions.visibilityOf(searchScreen.getEditFromPriceField()));
        searchScreen.getEditFromPriceField().sendKeys("100");

        searchScreen.getTapToTypeBottom().click();
        wait.until(ExpectedConditions.visibilityOf(searchScreen.getEditToPriceField()));
        searchScreen.getEditToPriceField().sendKeys("100");

        driver.hideKeyboard();
        searchScreen.waitForElement(searchScreen.getSearchButton());
        SearchResultScreen searchResultScreen = searchScreen.tapSearchButton();

        wait.until(ExpectedConditions.visibilityOf(searchResultScreen.getRefineSearchHeader()));
        searchResultScreen.waitForResultsToLoad();
        Assert.assertTrue(searchResultScreen.getSearchResultImages().size() >= 1);
        Assert.assertTrue(searchResultScreen.getFavoriteButtonsGridView().size() >= 1);
    }
}