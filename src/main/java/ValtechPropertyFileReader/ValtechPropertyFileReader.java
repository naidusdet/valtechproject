package ValtechPropertyFileReader;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static valtech.valtechconstants.ValtechConfigConstants.*;

/**
 * Created by maheshboyalla on 16/01/2018.
 */
public class ValtechPropertyFileReader {

    public static String getBrowser(){
        return getTestRunConfigProperty(BROWSER_NAME);}

    public static String getBaseURL(){
        return getTestRunConfigProperty(BASE_URL);}

    public static String getLocationsCount(){
        return getTestRunConfigProperty(LOCATIONS_COUNT);}


    public static String getTestRunConfigProperty(String propertyName) {
        Properties valtechTestConfigProperties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(TEST_CONFIG_PROP_FILE);
            valtechTestConfigProperties.load(input);
            return valtechTestConfigProperties.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return TEST_CONFIG_PROPERTY + propertyName + DOES_NOT_EXISTS;
    }



}
