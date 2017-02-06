package ios.smoke;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.SearchResultScreen;
import screens.ios.SearchScreen;
import util.factory.EyesProvider;

/**
 * For both colors and fancy colors
 */
public class SupplierAndLocationSearchTest extends iOSBaseTest {

    private final String country = "Israel";
    private final String supplier = "Yes Diamonds";

    @Test(dataProvider = "ios")
    public void supplierAndLocationSearchTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.getMoreButton().click();
        searchScreen.getSearchTextBar().sendKeys(country);
        searchScreen.getIsraelLocationSelection().click();
        searchScreen.getLocationSupplierDoneButton().click();
        searchScreen.getSupplierTabButton().click();
        searchScreen.getSearchTextBar().sendKeys(supplier);
        searchScreen.getParentRowSupplierSearchResult().findElement(By.id(supplier)).click();
        searchScreen.getLocationSupplierDoneButton().click();
        searchScreen.getColorButton().click();
        SearchResultScreen searchResultScreen = searchScreen.tapSearchButton();
        searchResultScreen.waitForSearchResultsToLoad();
        searchResultScreen.getListviewButton().click();
        searchResultScreen.getEyeIconButtons().get(0).click();
        Assert.assertTrue("Did we successfully search for a specific Israeli Supplier?",
                driver.findElements(By.id(supplier)).size() >= 1);
    }
}
