package utility;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ScreenShot extends TestBase {

    public static String getScreenshot() {
        Path path1 = Paths.get("./Screenshot/");
        // if directory exists?
        if (!Files.exists(path1)) {
            try {
                Files.createDirectories(path1);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }

        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/Screenshot/" +"Screenshot"+ System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}


