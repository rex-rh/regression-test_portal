package Helper;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.IOException;
import java.time.Duration;


public class JavaScriptUtil {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public void waitForPageLoad() {
        try {
            FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(25))
                    .pollingEvery(Duration.ofSeconds(3)).ignoring(java.util.NoSuchElementException.class);
            wait.until(driver -> {
                System.out.println("Current Window State  : " + ((JavascriptExecutor) driver).executeScript("return document.readyState"));
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            });
        } catch (Exception e) {
            System.out.println("Exception in method - | waitForPageLoad | " + e.getMessage());
        }
    }

    public void DOGetPageLoadTime() {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        double loadTime = (Double) js.executeScript("return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
        System.out.print(loadTime + " seconds");
    }

    public void DoScrollIntoView(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", (elementUtil.getElement(locator)));
    }

    public void DoClick(By locator) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", (elementUtil.getElement(locator)));
    }

    public void DoZoomPercentage(int Percentage) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.body.style.zoom = '" + Percentage + "%" + "';");
    }

    public void DoSendKeys(By locator, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value=" + value + ";", (elementUtil.getElement(locator)));
    }

    public void DatePickerJE(By locator, String Date) throws IOException, InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].type = arguments[1]", (elementUtil.getElement(locator)), "text");
        (elementUtil.getElement(locator)).clear();
        (elementUtil.getElement(locator)).sendKeys(Date);
    }
}
