package pages;

import Helper.ElementUtil;
import Helper.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class global_webelement {
    ElementUtil elementUtil;
    JavaScriptUtil javaScriptUtil;

    public static String HomeTittle = "Reliance Health | Affordable and accesible healthcare for everyone";
    By switch_global = By.xpath("//button[@class='chakra-button css-10l7qcd']");

    public global_webelement(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30),this);
        elementUtil = new ElementUtil(driver);
        javaScriptUtil = new JavaScriptUtil(driver);
    }

    public void switch_to_global_website() {
       javaScriptUtil.DoClick(switch_global);
    }
}
