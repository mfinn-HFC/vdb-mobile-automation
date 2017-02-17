package screens.android.data;

/**
 * Created by matt-hfc on 12/21/16.
 */
public enum ColorDepth
{

    FAINT("Faint"),
    VERY_LIGHT("Very Light"),
    LIGHT("Light"),
    FANCY("Fancy"),
    INTENSE("Intense"),
    VIVID("Vivid"),
    DEEP("Deep");

    private String name;

    ColorDepth(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}

