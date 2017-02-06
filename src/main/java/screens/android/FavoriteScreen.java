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
public class FavoriteScreen extends AndroidBaseScreen {

    @AndroidFindBy(id = "com.vdbapp.android:id/sign_up")
    private AndroidElement signUpButton;

    public final String descriptionsId = "com.vdbapp.android:id/collections_diamond_desc";
    @AndroidFindBy(id = descriptionsId)
    private List<AndroidElement> descriptions;

    @AndroidFindBy(id = "com.vdbapp.android:id/loved_check_box")
    private List<AndroidElement> lovedCheckboxes;

    @AndroidFindBy(id = "com.vdbapp.android:id/loved_collection_clear_button")
    private AndroidElement clearButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/buttonDefaultPositive")
    private AndroidElement okButton;

    @AndroidFindBy(id = "com.vdbapp.android:id/collections_diamond_desc")
    private List<AndroidElement> diamondDescriptions;

    public FavoriteScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public AndroidElement getSignUpButton() {
        return signUpButton;
    }

    public List<AndroidElement> getDescriptions() {
        return descriptions;
    }

    public List<AndroidElement> getLovedCheckboxes() {
        return lovedCheckboxes;
    }

    public AndroidElement getClearButton() {
        return clearButton;
    }

    public AndroidElement getOkButton() {
        return okButton;
    }

    public List<AndroidElement> getDiamondDescriptions() {
        return diamondDescriptions;
    }
}
