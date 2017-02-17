package android.favorite;

import base.AndroidBaseTest;
import junit.framework.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import screens.android.FavoriteScreen;
import screens.android.SearchResultScreen;
import screens.android.SearchScreen;
import screens.android.TermsAndConditionsScreen;
import util.factory.EyesProvider;


/**
 * Created by matt-hfc on 12/14/16.
 */
public class FavoriteFromSearchListViewTest extends AndroidBaseTest {

    public FavoriteFromSearchListViewTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void favoriteFromSearchListViewTest() throws InterruptedException {
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        eyes = eyesProvider.getEyes();
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
        searchScreen.waitForElement(searchScreen.getSearchButton());
        searchScreen.getSearchButton().click();
        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);

        wait.until(ExpectedConditions.visibilityOf(searchResultScreen.getRefineSearchHeader()));
        searchResultScreen.tapListView();
        searchResultScreen.waitForResultsToLoad();

        searchResultScreen.getFavoriteButtonsListView().get(0).click();
        eyes.checkWindow("One favorite added");
        searchResultScreen.getFavoriteButtonsListView().get(1).click();
        eyes.checkWindow("Two favorites added");
        searchResultScreen.getFavoriteButtonsListView().get(1).click();
        eyes.checkWindow("One favorite removed");
        searchResultScreen.getFavoriteMenuButton().click();

        FavoriteScreen favoriteScreen = new FavoriteScreen(driver);
        favoriteScreen.waitForElement(favoriteScreen.getLovedCheckboxes().get(0));
        Assert.assertTrue(favoriteScreen.getLovedCheckboxes().size() == 1);
    }
}