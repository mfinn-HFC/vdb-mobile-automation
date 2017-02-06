package ios.favorite;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.FavoriteScreen;
import screens.ios.SearchResultScreen;
import screens.ios.SearchScreen;
import screens.ios.TermsAndConditionsScreen;
import util.factory.EyesProvider;

/**
 * Created by matt-hfc on 1/17/17.
 *
 * Add two favorites from list view
 * Remove one favorite from list view
 * Go to favorite screen
 * Assert favorite is there
 * Remove favorite and assert no favorites are remaining
 */
public class FavoriteFromSearchListViewTest extends iOSBaseTest {

    @Test(dataProvider = "ios")
    public void favoriteFromSearchListViewTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        registerActivateNewUser();
        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.newUserLogin(user);
        termsAndConditionsScreen.acceptTerms();

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.getFromValueInput().sendKeys("100");
        searchScreen.getToValueInput().sendKeys("100");
        searchScreen.getToText().click();
        searchScreen.tapSearchButton();

        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);
        searchResultScreen.waitForSearchResultsToLoad();
        searchResultScreen.getListviewButton().click();
        searchResultScreen.waitForListViewToLoad();

        searchResultScreen.getFavoriteButtonsListView().get(0).click();
        eyes.checkWindow("One favorite added");
        searchResultScreen.getFavoriteButtonsListView().get(1).click();
        eyes.checkWindow("Two favorites added");
        searchResultScreen.getFavoriteButtonsListView().get(1).click();
        eyes.checkWindow("One favorite removed");
        searchResultScreen.getFavoriteToolbarButton().click();

        FavoriteScreen favoriteScreen = new FavoriteScreen(driver);
        wait.until(ExpectedConditions.elementToBeClickable(favoriteScreen.getTrashButton()));
        Assert.assertTrue(favoriteScreen.getBookmarkChecks().size() == 1);
        favoriteScreen.getBookmarkChecks().get(0).click();
        favoriteScreen.getTrashButton().click();
        favoriteScreen.getYesButtonDelete().click();

        wait.until(ExpectedConditions.elementToBeClickable(favoriteScreen.getTrashButton()));
        Assert.assertTrue(favoriteScreen.getBookmarkChecks().size() == 0);
    }
}
