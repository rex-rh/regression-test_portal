package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import testrail.TestRailHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static mailsac.RestCalls.*;
import static org.hamcrest.Matchers.lessThan;
import static utility.Utility.fetchvalue;

public class TestBase {
    public static WebDriver driver;
    public TestRailHandler trh;

    public static ResponseSpecification responseSpec_200;
    public ResponseSpecification responseSpec_400;
    public ResponseSpecification responseSpec_401;
    public ResponseSpecification responseSpec_404;
    public ResponseSpecification responseSpec_409;
    public ResponseSpecification responseSpec_403;

    String testrailusername = System.getenv("TESTRAIL_USERNAME") != null ? System.getenv("TESTRAIL_USERNAME")
            : fetchvalue("TESTRAIL_USERNAME");
    String testrailpassword = System.getenv("TESTRAIL_PASSWORD") != null ? System.getenv("TESTRAIL_PASSWORD")
            : fetchvalue("TESTRAIL_PASSWORD");
    String testrailurl = System.getenv("TESTRAIL_URL") != null ? System.getenv("TESTRAIL_URL")
            : fetchvalue("TESTRAIL_URL");

    @Parameters({"BrowserName", "browserVersion", "platform"})
    @BeforeTest(alwaysRun = true)
    public void SetUp(String BrowserName, String browserVersion, String platform) {
        if (BrowserName.equalsIgnoreCase("RemoteChrome")) {
            try {
                ChromeOptions options = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(fetchvalue("RemoteURL")), options);
                options.setBrowserVersion(browserVersion);
                options.setPlatformName(platform);
                options.setCapability("enableVNC", true);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (BrowserName.equalsIgnoreCase("RemoteFirefox")) {
            try {
                FirefoxOptions options = new FirefoxOptions();

                driver = new RemoteWebDriver(new URL(fetchvalue("RemoteURL")), options);
                options.setBrowserVersion(browserVersion);
                options.setPlatformName(platform);
                options.setCapability("enableVNC", true);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }

        if (BrowserName.equalsIgnoreCase("RemoteEdge")) {
            try {
                EdgeOptions options = new EdgeOptions();
                driver = new RemoteWebDriver(new URL(fetchvalue("RemoteURL")), options);
                options.setBrowserVersion(browserVersion);
                options.setPlatformName(platform);
                options.setCapability("enableVNC", true);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (BrowserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

        }

        if (BrowserName.equalsIgnoreCase("Headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
//            options.setHeadless(true);
            options.addArguments("windows-size=1280,880");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

        }

        if (BrowserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            System.out.println("Running Test On Firefox");

        }

        if (BrowserName.equalsIgnoreCase("Safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
            System.out.println("Running Test On safari");
        }
        driver.manage().window().maximize();
        driver.get(fetchvalue("MonoNGURL"));

     //   driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(fetchvalue("PageLoad.wait"))));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(fetchvalue("implicit.wait"))));
    }

    @BeforeSuite(alwaysRun = true)
    public void LinkTestrail() {
        {
            try {
                trh = new TestRailHandler(testrailusername, testrailpassword, testrailurl);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    protected void setUpConfiguration() {
        RestAssured.baseURI = fetchvalue("mailsacBaseURL");

        responseSpec_200 = new ResponseSpecBuilder().
                expectStatusCode(RESPONSE_STATUS_CODE_200).
                expectContentType(ContentType.JSON).expectStatusLine(StatusLine_200)
                .expectResponseTime(lessThan(15L), TimeUnit.SECONDS).
                build();

        responseSpec_400 = new ResponseSpecBuilder().
                expectStatusCode(RESPONSE_STATUS_CODE_400).
                expectContentType(ContentType.JSON).expectStatusLine(StatusLine_400)
                .expectResponseTime(lessThan(15L), TimeUnit.SECONDS).
                build();

        responseSpec_403 = new ResponseSpecBuilder().
                expectStatusCode(RESPONSE_STATUS_CODE_403).
                expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(15L), TimeUnit.SECONDS).
                build();

        responseSpec_404 = new ResponseSpecBuilder().
                expectStatusCode(RESPONSE_STATUS_CODE_404)
                .expectStatusLine(StatusLine_404)
                .expectResponseTime(lessThan(15L), TimeUnit.SECONDS).
                build();

        responseSpec_409 = new ResponseSpecBuilder().
                expectStatusCode(RESPONSE_STATUS_CODE_409).
                expectContentType(ContentType.JSON).expectStatusLine(StatusLine_400)
                .expectResponseTime(lessThan(15L), TimeUnit.SECONDS).
                build();

        responseSpec_401 = new ResponseSpecBuilder().
                expectStatusCode(401).
                build();
    }

    @AfterTest(alwaysRun = true)
    public void Quit() {
        driver.quit();
    }

}