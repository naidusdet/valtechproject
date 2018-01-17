package valtech.valtechenums;

/**
 * Created by maheshboyalla on 16/01/2018.
 */
public enum ValtechBrowser {

    IE,
    Firefox,
    Chrome;

    public static ValtechBrowser lookup(String browserNName) {
        boolean found = false;
        String name = values().toString();
        for (ValtechBrowser valtechBrowser : ValtechBrowser.values()) {
            String browserString = valtechBrowser.name().toString();
            if (browserString.equalsIgnoreCase(browserNName)) {
                found = true;
                return valtechBrowser;
            }
        }
        if (!found) throw new RuntimeException("Invalid value for my enum: " + browserNName);
        return null;
    }
}
