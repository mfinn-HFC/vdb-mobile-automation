package screens.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import screens.AndroidBaseScreen;

import java.util.List;

/**
 * Created by matt-hfc on 12/21/16.
 */
public class DiamondViewScreen extends AndroidBaseScreen {

    @AndroidFindBy(id = "com.vdbapp.android:id/favorite_button")
    private AndroidElement favoriteButton;

    @AndroidFindBy(id = "Navigate up")
    private AndroidElement backButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/collections_diamond_desc")
    private List<AndroidElement> checkMarkButtons;

    @AndroidFindBy(id = "com.vdbapp.android:id/color")
    private AndroidElement colorParentElement;

    public DiamondViewScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getFavoriteButton() {
        return favoriteButton;
    }

    public AndroidElement getBackButton() {
        return backButton;
    }

    public List<AndroidElement> getCheckMarkButtons() {
        return checkMarkButtons;
    }

    public AndroidElement getColorParentElement() {
        return colorParentElement;
    }
}
