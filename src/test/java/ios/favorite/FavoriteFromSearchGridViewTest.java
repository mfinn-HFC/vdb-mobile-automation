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
 * Add two favorites from grid view
 * Remove one favorite from grid view
 * Go to favorite screen
 * Assert favorite is there
 * Remove favorite and assert no favorites are remaining
 */
public class FavoriteFromSearchGridViewTest extends iOSBaseTest {

    @Test(dataProvider = "ios")
    public void favoriteFromSearchTest(DesiredCapabilities capabilities) throws InterruptedException {
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

        searchResultScreen.getFavoriteButtonGridView().get(0).click();
        eyes.checkWindow("One favorite added");
        searchResultScreen.getFavoriteButtonGridView().get(1).click();
        eyes.checkWindow("Two favorites added");
        searchResultScreen.getFavoriteButtonGridView().get(1).click();
        eyes.checkWindow("One favorite removed");
        searchResultScreen.getFavoriteToolbarButton().click();

        FavoriteScreen favoriteScreen = new FavoriteScreen(driver);
        wait.until(ExpectedConditions.elementToBeClickable(favoriteScreen.getTrashButton()));
        Assert.assertTrue(favoriteScreen.getBookmarkChecks().size() == 1);
    }
}
