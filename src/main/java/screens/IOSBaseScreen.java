package screens;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class IOSBaseScreen {

    protected IOSDriver driver;

    protected void dismissBugReportNotification()
    {
        // Dismiss the stupid 'shake to report a bug' notification
        int i = 0;
        boolean clicked = false;
        while(driver.findElements(By.id("OK")).size() == 0) {
            try {
                Thread.sleep(0500);
                driver.findElement(By.id("OK")).click();
                clicked = true;
            } catch (Exception e) {
                System.out.println("Attempting to dismiss bug report, attempt: " + i);
            }
            i++;
            if(i > 20) break;
            if(clicked) break;
        }
    }

}
