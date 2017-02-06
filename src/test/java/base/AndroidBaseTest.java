package base;

import api.ActivateUserClient;
import api.User;
import com.applitools.eyes.Eyes;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import model.TestEnvironment;
import model.TestdroidEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import screens.android.LoginScreen;
import screens.android.RegistrationScreen;
import util.WaitBy;
import util.factory.CapabilitiesFactory;
import util.factory.DriverFactory;
import util.factory.EnvironmentFactory;
import util.factory.EyesProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by matt-hfc on 12/14/16.
 */
public class AndroidBaseTest <T extends TestEnvironment> {

    protected static String testServer;
    protected AndroidDriver driver;
    protected Eyes eyes;
    protected EyesProvider eyesProvider;
    protected final String appName = "VDB iOS";
    protected WebDriverWait wait;
    protected LoginScreen loginScreen; // Every test will land on this screen initially
    protected WaitBy waitBy;

    private CapabilitiesFactory capabilitiesFactory = new CapabilitiesFactory();
    private EnvironmentFactory environmentFactory = new EnvironmentFactory();

    protected String email = generateEmail();
    protected User user;

    // Should return the list of drivers
    @DataProvider(name = "drivers")
    public Object[][] setEnvironments() throws FileNotFoundException, IllegalAccessException, NoSuchFieldException, MalformedURLException
    {
        Object[][] array = null;
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader("/Users/matt-hfc/IdeaProjects/vdb-android-automation/src/test/resources/androidEnvironments.json"));
        testServer = jsonElement.getAsJsonObject().get("testServer").getAsString();

        try
        {
            int index = 0;
            if (jsonElement.getAsJsonObject().get("remote").getAsString().contains("true"))
            {
                try
                {
                    List<TestdroidEnvironment> testEnvironmentList =
                            (List<TestdroidEnvironment>) environmentFactory.getTestEnvironmentsFromJSON(jsonElement.getAsJsonObject(), TestdroidEnvironment.class);

                    for(TestdroidEnvironment testEnvironment : testEnvironmentList)
                    {
                        DesiredCapabilities capabilities = new CapabilitiesFactory<>().getCapabilities(testEnvironment);
                        array[index][0] = capabilities;
                        index++;
                    }
                }
                catch (Exception e) { System.out.println(e.getMessage()); }
            }
            else
            {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                // App API path via CircleCI something like https://circleci.com/api/v1/project/HappyFunCorp/illuminex-android/latest/artifacts
                capabilities.setCapability("app", jsonElement.getAsJsonObject().get("appPath"));
                capabilities.setCapability("deviceName", "emulator-5554");
                capabilities.setCapability("fullReset", "true");
                driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
                array = new Object[1][1];
                array[0] = new Object[]{ driver };
            }
        }
        catch (Exception e) { System.out.println( e.getMessage() ); array = new Object[1][1]; }
        return array;
    }

    public void setUp(DesiredCapabilities capabilities, Class clazz)
    {
        try
        {
            this.driver = (AndroidDriver) DriverFactory.getDrivers(capabilities, testServer, clazz);
        }
        catch (MalformedURLException e) { System.out.println(e.getMessage()); }

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
        long milliseconds = System.currentTimeMillis() % 100000;
        String email = "vdb.appium+" + String.valueOf(milliseconds) + "@gmail.com";
        return email;
    }

    public void hideKeyboard() {
        try
        {
            driver.hideKeyboard();
        }
        catch (WebDriverException ex) { driver.pressKeyCode(66); }
        catch (Exception ex) { /** If keyboard isn't visible **/ }    }

    public void registerActivateNewUser() throws InterruptedException
    {
        loginScreen.getSignUpButton().click();

        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.fillRegistrationForm(email);

        user = new User();
        user.setEmail(email);
        user.setActive(true);
        Assert.assertTrue("User was activated via API call", ActivateUserClient.setUserActiveStatus(user));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
