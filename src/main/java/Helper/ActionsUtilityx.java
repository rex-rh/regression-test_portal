package Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;

import static Helper.ElementUtil.getElement;

public class ActionsUtilityx {
    private JavaScriptUtil javaScriptUtil;
    private static Actions act;
    private static WebDriver driver;

    public ActionsUtilityx(WebDriver driver) {
        ActionsUtilityx.driver = driver;
        act = new Actions(driver);
    }


    public static void waitForElementClickable(By locator, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(getElement(locator)));
    }

    public static boolean isDisplayed (By locator){
        waitForElementClickable(locator, 20);
        String elementText = getElement(locator).getText();
        boolean displayed = getElement(locator).isDisplayed();
        if (displayed == true) {
            Assert.assertTrue(true, "The expected " + elementText + " was displayed");
            System.out.println("The expected - "  + elementText + " - was displayed");
            return true;
        }
        else {
            System.out.println("Element " + elementText + " was not displayed");
            return false;
        }
    }

    public static void openNewtab(){
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public static void closeTab(){
        driver.close();
    }

    public static void switch_tabs(int index){
        // hold all window handles in array list
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(tabs.get(index));
        System.out.println("Page title of this tab is : " + driver.getTitle());
    }

}
