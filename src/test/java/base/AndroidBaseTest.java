package base;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.appium.java_client.android.AndroidDriver;
import model.TestEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import screens.android.LoginScreen;
import util.WaitBy;
import util.factory.CapabilitiesFactory;
import util.factory.DriverFactory;
import util.factory.EnvironmentFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class AndroidBaseTest {

    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected LoginScreen loginScreen; // Every test will land on this screen initially
    protected WaitBy waitBy;

    private CapabilitiesFactory capabilitiesFactory = new CapabilitiesFactory();
    private EnvironmentFactory environmentFactory = new EnvironmentFactory();

    // Should return the list of drivers
    @DataProvider(name = "drivers")
    public Object[][] setEnvironments() throws FileNotFoundException, IllegalAccessException, NoSuchFieldException, MalformedURLException {
        Object[][] array;
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader("/Users/matt-hfc/IdeaProjects/vdb-android-automation/src/test/resources/androidEnvironments.json"));

        try
        {
            if (jsonElement.getAsJsonObject().get("remote").equals("true"))
            {
                List<TestEnvironment> testEnvironmentList = environmentFactory.getTestEnvironmentsFromJSON(jsonElement.getAsJsonObject());
                array = DriverFactory.getDrivers(capabilitiesFactory.getCapabilities(testEnvironmentList));
            }
            else
            {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                // App API path via CircleCI something like https://circleci.com/api/v1/project/HappyFunCorp/illuminex-android/latest/artifacts
                capabilities.setCapability("app", jsonElement.getAsJsonObject().get("appPath"));
                capabilities.setCapability("deviceName", "09605dca029b1887");
                driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
                array = new Object[1][1];
                array[0] = new Object[]{ driver };
            }
        }
        catch (Exception e) { System.out.println( e.getMessage() ); array = new Object[1][1]; }

        return array;
    }

    public void setUp(RemoteWebDriver driver) {
        this.driver = (AndroidDriver) driver;
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        loginScreen = new LoginScreen(this.driver);
        waitBy = new WaitBy(this.driver);
    }

    public void waitForBugReportPromptToClose() {
        wait.until(ExpectedConditions.visibilityOf(loginScreen.getUsernameEditText()));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));
        wait.until(ExpectedConditions.visibilityOf(loginScreen.getUsernameEditText()));
    }

    public String generateEmail() {
        long milliseconds = System.currentTimeMillis() % 1000;
        String email = "vdb.appium+" + String.valueOf(milliseconds) + "@gmail.com";
        return email;
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
