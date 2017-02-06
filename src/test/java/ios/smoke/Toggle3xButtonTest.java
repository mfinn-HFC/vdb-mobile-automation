package ios.smoke;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.SearchScreen;
import sun.security.krb5.internal.crypto.Des;
import util.factory.EyesProvider;

/**
 * For both colors and fancy colors
 */
public class Toggle3xButtonTest extends iOSBaseTest {

    private final String textClassName = "UIAStaticText"; // For 3x text there's no button, hit the text

    @Test(dataProvider = "ios")
    public void toggle3xButtonTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.getMoreButton().click();
        searchScreen.getPolishSymmetrySearchButton().click();
        eyes.checkWindow("Before turning 3x ON");
        driver.findElement(By.className(textClassName)).click();
        eyes.checkWindow("3x ON for Polish");
        searchScreen.getSymmetryTabButton().click();
        eyes.checkWindow("3x ON for Symmetry");
        searchScreen.getCutGradeTabButton().click();
        eyes.checkWindow("3x ON for Cut Grade");
        driver.findElement(By.className(textClassName)).click();
        eyes.checkWindow("3x toggled OFF - Cut Grade");
        searchScreen.getSymmetryTabButton().click();
        eyes.checkWindow("3x OFF for Symmetry");
        searchScreen.getPolishTabButton().click();
        eyes.checkWindow("3x OFF for Polish");
    }
}
