package android.favorite;

import base.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.android.FavoriteScreen;
import screens.android.SearchResultScreen;
import screens.android.SearchScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class FavoriteFromSearchTest extends AndroidBaseTest {

    @Test(dataProvider = "drivers")
    public void favoriteFromSearchTest(AndroidDriver driver) throws InterruptedException {
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
        wait.until(ExpectedConditions.elementToBeClickable(searchScreen.getSearchButton()));
        searchScreen.getSearchButton().click();
        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);

        wait.until(ExpectedConditions.visibilityOf(searchResultScreen.getRefineSearchHeader()));
        // This test can't work unless there's text in the description
        String resultDescription = searchResultScreen.getSearchResultDescriptions().get(0).getText();
        Assert.assertNotNull(resultDescription);

        searchResultScreen.getFavoriteButtons().get(0).click();
        searchResultScreen.getFavoriteMenuButton().click();
        FavoriteScreen favoriteScreen = new FavoriteScreen(driver);
        waitBy.waitByLocator(By.id(favoriteScreen.descriptionsId));
        Assert.assertTrue(favoriteScreen.getDescriptions().get(0).getText() == resultDescription);
        favoriteScreen.getLovedCheckboxes().get(0).click();
        favoriteScreen.getClearButton().click();
        favoriteScreen.getOkButton().click();
        favoriteScreen.getOkButton().click();

        Assert.assertFalse(favoriteScreen.getDescriptions().contains(resultDescription));
    }
}