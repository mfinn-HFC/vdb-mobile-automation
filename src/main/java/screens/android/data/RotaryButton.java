package screens.android.data;

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
public enum RotaryButton
{

    BUDGET("Budget"),
    SHAPE("Shape"),
    CARAT("Carat"),
    COLOR("Color"),
    CLARITY("Clarity");

    private String name;

    RotaryButton(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}

