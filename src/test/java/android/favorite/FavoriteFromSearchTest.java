package android.favorite;

import base.AndroidBaseTest;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import screens.android.FavoriteScreen;
import screens.android.SearchResultScreen;
import screens.android.SearchScreen;
import screens.android.TermsAndConditionsScreen;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class FavoriteFromSearchTest extends AndroidBaseTest {

    public FavoriteFromSearchTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void favoriteFromSearchTest() throws InterruptedException {
        waitForBugReportPromptToClose();

        registerActivateNewUser();

        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.loginNewUser(user);
        SearchScreen searchScreen = termsAndConditionsScreen.acceptTerms();

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
        searchResultScreen.waitForResultsToLoad();

        // This test can't work unless there's text in the description
        String resultDescription = searchResultScreen.getSearchResultDescriptions().get(0).getText();
        Assert.assertNotNull(resultDescription);

        searchResultScreen.getFavoriteButtonsGridView().get(0).click();
        searchResultScreen.getFavoriteMenuButton().click();
        FavoriteScreen favoriteScreen = new FavoriteScreen(driver);
        waitBy.waitByLocator(By.id(favoriteScreen.descriptionsId));
        System.out.println(favoriteScreen.getDescriptions().get(0).getText());
        System.out.println(resultDescription);
        Assert.assertTrue(favoriteScreen.getDescriptions().get(0).getText().contains(resultDescription));
        favoriteScreen.getLovedCheckboxes().get(0).click();
        favoriteScreen.getClearButton().click();
        favoriteScreen.getOkButton().click();
        favoriteScreen.getOkButton().click();

        Assert.assertFalse(favoriteScreen.getDescriptions().contains(resultDescription));
    }
}