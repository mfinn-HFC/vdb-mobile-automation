package ios.favorite;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.*;

/**
 * Created by matt-hfc on 1/17/17.
 * TODO: See ILL-3362 and update when resolved - enable eyes check, remove extra steps
 */
public class FavoriteFromDiamondViewTest extends iOSBaseTest {

    private final String mp4DiamondCertNumber = "6241438392";
    private final String mp4DiamondTitle = "CU 1.41 F SI2 / $4,766";

    @Test(dataProvider = "ios")
    public void favoriteFromDiamondViewTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        //eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        //eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        registerActivateNewUser();
        TermsAndConditionsScreen termsAndConditionsScreen = loginScreen.newUserLogin(user);
        termsAndConditionsScreen.acceptTerms();

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.getLabButton().click();
        waitForBugReportPromptToClose();
        searchScreen.getCertNumberField().sendKeys(mp4DiamondCertNumber);
        searchScreen.getLabSearchButton().click();

        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);
        searchResultScreen.waitForSearchResultsToLoad();
        driver.findElement(By.id(mp4DiamondTitle)).click();

        // Favorite the diamond and check that the icon updated
        DiamondViewScreen diamondViewScreen = new DiamondViewScreen(driver);
        diamondViewScreen.getFavoriteButton().click();
        Assert.assertTrue(diamondViewScreen.getFavoriteAddedAlert().isDisplayed());
        diamondViewScreen.getFavoriteAddedOKButton().click();
        diamondViewScreen.getBackButton().click();
        //eyes.checkWindow("Heart Icon");

        searchResultScreen.getFavoriteToolbarButton().click();
        FavoriteScreen favoriteScreen = new FavoriteScreen(driver);
        wait.until(ExpectedConditions.elementToBeClickable(favoriteScreen.getTrashButton()));
        Assert.assertTrue(favoriteScreen.getBookmarkChecks().size() >= 1);
        Assert.assertTrue(driver.findElements(By.id(mp4DiamondTitle)).size() >= 1);
    }
}
