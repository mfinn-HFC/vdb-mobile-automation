package ios.smoke;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.DiamondViewScreen;
import screens.ios.SearchResultScreen;
import screens.ios.SearchScreen;
import util.factory.EyesProvider;

/**
 * Created by matt-hfc on 1/17/17.
 */
public class mp4PlayTest extends iOSBaseTest {

    private final String mp4DiamondCertNumber = "6241438392";
    private final String mp4DiamondTitle = "CU 1.41 F SI2 / $4,766";
    private final String gDriveDiamondCertNumber = "2175269488";
    private final String gDriveDiamondTitle = "PS 0.61 FCY YEL / $2,135";

    @Test(dataProvider = "ios")
    public void mp4PlayTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        SearchScreen searchScreen = new SearchScreen(driver);
        Assert.assertTrue(searchScreen.getFromValueInput().isDisplayed());

        // Check hamburger menu, then search by cert number
        searchScreen.getHamburgerButton().click();
        eyes.checkWindow("Hamburger Menu");
        searchScreen.getHamburgerButton().click();
        searchScreen.getLabButton().click();
        searchScreen.getCertNumberField().sendKeys(mp4DiamondCertNumber);
        driver.hideKeyboard();
        SearchResultScreen searchResultScreen = searchScreen.tapSearchButton();

        Assert.assertTrue(driver.findElements(By.id(mp4DiamondTitle)).size() >= 1);
        driver.findElement(By.id(mp4DiamondTitle)).click();

        DiamondViewScreen diamondViewScreen = new DiamondViewScreen(driver);
        diamondViewScreen.getVideoButton().click();
        eyes.checkWindow(mp4DiamondCertNumber + " mp4 Video");
        diamondViewScreen.getBackButton().click();
        searchResultScreen.getBackButton().click();

        searchScreen.getCertNumberField().clear();
        searchScreen.getCertNumberField().sendKeys(gDriveDiamondCertNumber);
        searchScreen.tapSearchButton();
        Assert.assertTrue(driver.findElements(By.id(gDriveDiamondTitle)).size() >= 1);
        driver.findElement(By.id(gDriveDiamondTitle)).click();

        /**
         * Need a way to get out of the native video player, seems really tricky.
         *
        diamondViewScreen.getVideoButton().click();
        diamondViewScreen.getClickToPlayButton().click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Done"))));
        eyes.checkWindow(gDriveDiamondCertNumber + " Gdrive Video");
        diamondViewScreen.getBackButton().click();
         **/

    }
}
