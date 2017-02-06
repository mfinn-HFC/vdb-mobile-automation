package ios.search;

import base.iOSBaseTest;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import screens.ios.SearchResultScreen;
import screens.ios.SearchScreen;
import util.factory.EyesProvider;

/**
 * Created by matt-hfc on 1/20/17.
 */
public class VisualCheckOfRefineSearchTest extends iOSBaseTest {

    @Test(dataProvider = "ios")
    public void visualCheckOfRefineSearchTest(DesiredCapabilities capabilities) throws InterruptedException {
        setUp(capabilities, this.getClass());
        eyesProvider = new EyesProvider(driver, appName, this.getClass().getSimpleName());
        eyes = eyesProvider.getEyes();

        loginScreen.acceptNotificationsButton();
        loginScreen.defaultLogin();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextId)));

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.tapSearchButton();

        SearchResultScreen searchResultScreen = new SearchResultScreen(driver);
        searchResultScreen.waitForSearchResultsToLoad();

        searchResultScreen.getRefineSearchButton().click();
        searchResultScreen.refineSearchBar().getBudgetButton().click();
        eyes.checkWindow("Refine Budget");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getShapeButton().click();
        eyes.checkWindow("Refine Shape");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getCaratButton().click();
        eyes.checkWindow("Refine Carat");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getColorButton().click();
        eyes.checkWindow("Refine Color");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getClarityButton().click();
        eyes.checkWindow("Refine Clarity");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getMoreButton().click();
        eyes.checkWindow("More Refine Options");

        searchResultScreen.refineSearchBar().getPolishSymmetryButton().click();
        eyes.checkWindow("Refine Polish/Symmetry");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getLabButton().click();
        eyes.checkWindow("Refine Lab");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getEnhancementButton().click();
        eyes.checkWindow("Refine Enhancement");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getTableDepthButton().click();
        eyes.checkWindow("Refine Table/Depth");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getFluorescenceButton().click();
        eyes.checkWindow("Refine Fluorescence");
        searchResultScreen.refineSearchBar().getRefineButton().click();

        searchResultScreen.refineSearchBar().getLessButton().click();
        eyes.checkWindow("Return to original Refine Screen");
    }
}
