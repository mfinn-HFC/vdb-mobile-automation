package ios.login;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

/**
 * Created by matt-hfc on 1/17/17.
 */
public class LoginTest extends iOSBaseTest {

    @Test(dataProvider = "ios")
    public void loginTest(IOSDriver driver) throws InterruptedException {
        setUp(driver);

        loginScreen.acceptNotificationsButton();
        loginScreen.login();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        screens.ios.SearchScreen searchScreen = new screens.ios.SearchScreen(driver);
        Assert.assertTrue(searchScreen.getFromValueInput().isDisplayed());
    }
}
