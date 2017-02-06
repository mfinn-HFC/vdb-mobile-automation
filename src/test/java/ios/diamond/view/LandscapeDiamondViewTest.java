package ios.diamond.view;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.DiamondViewScreen;
import screens.ios.SearchScreen;
import util.factory.EyesProvider;

/**
 * Created by matt-hfc on 1/17/17.
 * NOTE: Current appium version, due to changes in XCode, cannot support device rotation at this time.
public class LandscapeDiamondViewTest extends iOSBaseTest {

    private final String mp4DiamondCertNumber = "6241438392";
    private final String mp4DiamondTitle = "CU 1.41 F SI2 / $4,766";

    @Test(dataProvider = "ios")
    public void landscapeDiamondViewTest(IOSDriver driver) throws InterruptedException {
        setUp(driver);
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        //eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        SearchScreen searchScreen = new SearchScreen(driver);
        Assert.assertTrue(searchScreen.getFromValueInput().isDisplayed());
        searchScreen.getLabButton().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(searchScreen.fromValueInputID)));
        searchScreen.getCertNumberField().sendKeys(mp4DiamondCertNumber);
        searchScreen.getLabSearchButton().click();

        Assert.assertTrue(driver.findElements(By.id(mp4DiamondTitle)).size() >= 1);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.findElement(By.id(mp4DiamondTitle)).click();

        DiamondViewScreen diamondViewScreen = new DiamondViewScreen(driver);

    }
}

 */
