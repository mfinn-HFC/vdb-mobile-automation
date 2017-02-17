package base;

import api.ActivateUserClient;
import api.User;
import com.applitools.eyes.Eyes;
import enums.DeviceType;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testobject.appium.TestObjectListenerProvider;
import org.testobject.appium.testng.TestObjectTestNGTestResultWatcher;
import org.testobject.appium.testng.TestObjectWatcherProvider;
import screens.android.LoginScreen;
import screens.android.RegistrationScreen;
import util.TestObjectDeviceClient;
import util.WaitBy;
import util.factory.EyesProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by matt-hfc on 12/14/16.
 */
@Listeners({ TestObjectTestNGTestResultWatcher.class })
public class AndroidBaseTest implements TestObjectWatcherProvider {

    protected static DeviceType deviceType = DeviceType.ANDROID;
    protected static String testServer = "http://appium.testobject.com/wd/hub";
    protected AndroidDriver driver;
    protected Eyes eyes;
    protected EyesProvider eyesProvider;
    protected final String appName = "VDB iOS";
    protected WebDriverWait wait;
    protected LoginScreen loginScreen; // Every test will land on this screen initially
    protected WaitBy waitBy;
    protected static boolean remote;
    protected DesiredCapabilities capabilities;

    private TestObjectListenerProvider provider = TestObjectListenerProvider.newInstance();
    protected static TestObjectDeviceClient testObjectDeviceClient = new TestObjectDeviceClient();

    protected String email = generateEmail();
    protected User user;

    private AndroidBaseTest() {}

    public AndroidBaseTest(DesiredCapabilities capabilities)
    {
        String device = testObjectDeviceClient.waitForDeviceAvailability(deviceType);
        capabilities.setCapability("testobject_device", device);
        capabilities.setCapability("testobject_api_key", "532436C44BDD4FA29A6F2863007B294A");
        capabilities.setCapability("testobject_appium_version", "1.4.13");

        try
        {
            driver = new AndroidDriver(new URL(testServer), capabilities);
        }
        catch (MalformedURLException e)
        {
            // Not sure what we could do to recover here
            System.out.println("***Malformed URL Exception! Is the testServer correctly formatted? Check environments***");
            System.out.println(e.getMessage());
        }
        catch (WebDriverException ex)
        {
            System.out.println(ex.getMessage());
        }

        provider.setDriver(driver);
        provider.setLocalTest(false);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        loginScreen = new LoginScreen(driver);
        waitBy = new WaitBy(driver);
    }

    public void waitForBugReportPromptToClose() {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(loginScreen.getUsernameEditText()));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(loginScreen.usernameEditTextID)));
            wait.until(ExpectedConditions.visibilityOf(loginScreen.getUsernameEditText()));

        }
        catch (Exception e)
        {
            System.out.println("Did not see bug prompt - attempting to continue with test");
        }
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
        ActivateUserClient.setUserActiveStatus(user);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
        testObjectDeviceClient.releaseCurrentDevice();
    }

    @Override
    public TestObjectListenerProvider getProvider() {
        return provider;
    }
}
