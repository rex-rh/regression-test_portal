package Helper;

import Base.TestBase;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.portals_elements.customer_feeds_webElement;

import java.util.ArrayList;

public class ElementUtil {
    private static WebDriver driver;
    private JavaScriptUtil javaScriptUtil;
    private static Actions act;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        act = new Actions(driver);
    }
    /**
     * THIS METHOD WILL RETURN WEB ELEMENT FROM BY -
     */
    public static WebElement getElement(By elementLocation) {
        WebElement element = null;
        try {
            element = driver.findElement(elementLocation);
         //   element = getdriver.get().findElement(elementLocation);
        } catch (Exception e) {
            System.out.println(e);
        }
        return element;
    }

    public static void doActionsClick(By locator) {
        act.click(getElement(locator)).build().perform();
    }

    public static void doActionsSendkeys(By locator, String value) {
        act.sendKeys(getElement(locator), value).build().perform();
    }

//    public static void waitForElementClickable(By locator, int seconds){
//        WebDriverWait wait = new WebDriverWait(driver, seconds);
//        wait.until(ExpectedConditions.elementToBeClickable(getElement(locator)));
//    }
//
//    public static boolean isDisplayed (By locator){
//        waitForElementClickable(locator, 20);
//        String elementText = getElement(locator).getText();
//        boolean displayed = getElement(locator).isDisplayed();
//        if (displayed) {
//            Assert.assertTrue(displayed, "The expected " + elementText + " was displayed");
//            System.out.println("The expected - "  + elementText + " - was displayed");
//            return true;
//        }
//        else {
//            System.out.println("Element " + elementText + " was not displayed");
//            return false;
//        }
//    }

}
