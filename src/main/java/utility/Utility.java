package utility;

import Base.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utility extends TestBase {

    private static final String DATA_CONFIG = "./Config/config.properties";
    private static Properties properties;

    /**
     * This method is used to Fetch values from the config files
     * @return
     */
    public static String fetchvalue(String value) {
        try {
            if (properties == null) {
                Utility.properties = new Properties();
                Utility.properties.load(Files.newInputStream(Paths.get(DATA_CONFIG)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Utility.properties.getProperty(value);
    }
}