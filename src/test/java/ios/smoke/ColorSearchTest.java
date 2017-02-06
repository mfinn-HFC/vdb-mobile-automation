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
 * For both colors and fancy colors
 */
public class ColorSearchTest extends iOSBaseTest {

    private final String color = "Z";
    private final String fancyColor = "DP OR";

    @Test(dataProvider = "ios")
    public void colorSearchTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        // Search Z color diamond and make sure the menu can expand
        SearchScreen searchScreen = new SearchScreen(driver);
        Assert.assertTrue(searchScreen.getFromValueInput().isDisplayed());
        searchScreen.getColorButton().click();
        Assert.assertNull(driver.findElements(By.id(color)));
        searchScreen.getLineDownButton().click();
        Assert.assertTrue(driver.findElements(By.id(color)).size() > 0);
        driver.findElement(By.id(color)).click();
        SearchResultScreen searchResultScreen = searchScreen.tapSearchButton();

        searchResultScreen.waitForSearchResultsToLoad();
        searchResultScreen.getListviewButton().click();
        searchResultScreen.getEyeIconButtons().get(0).click();

        DiamondViewScreen diamondViewScreen = new DiamondViewScreen(driver);
        Assert.assertTrue(diamondViewScreen.getShapeRow().findElements(By.id(color)).size() >= 1);
        eyes.checkWindow(color + " Color Diamond");
        diamondViewScreen.getBackButton().click();

        // Search fancy color Deep Orange / DP OR
        searchResultScreen.getRefineSearchButton().click();
        searchResultScreen.getFancyColorsButton().click();
        searchResultScreen.getFancyOrangeColorButton().click();
        searchResultScreen.getDeepColorButton().click();
        eyes.checkWindow("Fancy color selections made " + fancyColor);
        searchResultScreen.getRefineDoneButton().click();
        searchResultScreen.getEyeIconButtons().get(0).click();
        Assert.assertTrue(diamondViewScreen.getShapeRow().findElements(By.id(fancyColor)).size() >= 1);
        eyes.checkWindow(fancyColor + " Diamond");
    }
}
