package pages.portals_elements;

import Base.TestBase;
import Helper.ActionsUtilityx;
import Helper.ElementUtil;
import Helper.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import static Listeners.ExtentReport.logInfo;
import java.io.IOException;
import static Helper.ElementUtil.*;
import static Helper.ActionsUtilityx.*;
import static Helper.ElementUtil.getElement;
import static utility.Utility.fetchvalue;

public class customer_feeds_webElement extends TestBase{

    ElementUtil elementUtil;
    JavaScriptUtil javaScriptUtil;

    ActionsUtilityx actionsUtilityx;
    String validEmail = System.getenv("validEmail") != null ? System.getenv("validEmail")
            : fetchvalue("validEmail");
    String validPassword = System.getenv("validPassword") != null ? System.getenv("validPassword")
            : fetchvalue("validPassword");

    //Login Elements
    By emailField = By.xpath("//input[@placeholder='Email']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By submitButton = By.xpath("//button[normalize-space()='Sign In']");
    By warningAlert = By.xpath("/html/body/div/div[2]/div[1]/p");

    //Customer Satisfaction System
    By SideBar_CustomerFeeds = By.xpath("//span[normalize-space()='Customer Feeds']");
    By SideBar_AllCustomerFeeds = By.xpath("//a[normalize-space()='All Customer Feeds']");
    By SideBar_AllCustomerSatisfaction = By.xpath("//a[normalize-space()='All Customer Satisfaction']");

    //Create Prospect feed locators
    By AllCustomerFeeds_CreateFeeds = By.xpath("//button[normalize-space()='Create Feed']");
    By CreateFeeds_SelectCustomerType = By.xpath ("//div[contains(text(),'Select customer type')]");
    By CreateFeeds_CustomerType_Prospect = By.xpath ("//div[normalize-space()='Prospect']");
    By CreateFeeds_ProspectName = By.xpath("//*[@id=\"prospectName\"]");
    By CreateFeeds_ProspectEmail = By.xpath ("//input[@placeholder='Enter email address']"); //*[@id="prospectEmail"]
    By CreateFeeds_ContactMedium = By.xpath ("(//div)[34]");
    By CreateFeeds_ContactMedium_Email = By.xpath ("//div[normalize-space()='Email']");
//    By CreateFeeds_FeedType = By.xpath("/html/body/div[1]/div[2]/section[2]/div/section/form/div/div[2]/div/div/div[1]/div[1]/div/div/div/div[1]");
    By CreateFeeds_FeedType = By.xpath("//*[text()='Select type']"); // "//*[text()='select label']"
    By CreateFeeds_FeedType_Inquiry = By.xpath ("//div[normalize-space()='Inquiry']");
//    By CreateFeeds_FeedCategory = By.xpath ("/html/body/div[1]/div[2]/section[2]/div/section/form/div/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]");
    By CreateFeeds_FeedCategory = By.xpath ("//*[text()='Select Category']");

    By CreateFeeds_FeedCategory_Benefits= By.xpath ("//div[normalize-space()='Benefits']");
//    By CreateFeeds_FeedLabel = By.xpath ("/html/body/div[1]/div[2]/section[2]/div/section/form/div/div[2]/div/div/div[1]/div[3]/div/div/div/div[1]");
    By CreateFeeds_FeedLabel = By.xpath ("//*[text()='Select label']");
    By CreateFeeds_FeedLabel_BenefitsConfirmation = By.xpath ("//div[normalize-space()='Benefits confirmation']");
    By CreateFeeds_Description = By.xpath ("//textarea[@placeholder='Enter description']");   //*[@id="body"]/div[2]/div
    By CreateFeeds_SaveFeed = By.xpath ("//button[normalize-space()='Save Feed']");
    By CreateFeeds_Alert = By.xpath ("//body//div//div//div//div[contains(text(),'feed created successfully')]");  //feed created successfully
    By FeedsTable_FeedsLabel = By.cssSelector ("tbody tr:nth-child(1) td:nth-child(1) div:nth-child(1)");
    By FeedsTable_CustomerType = By.cssSelector("tbody tr:nth-child(1) td:nth-child(3)");
    By FeedsTable_ContactMedium = By.cssSelector( "tbody tr:nth-child(1) td:nth-child(4)");

    By CsatTable_EyeIcon = By.xpath ("//*[@id=\"allCsat\"]/section/div/div/div/table/tbody/tr[1]/td[8]/a");
    By CsatTable_Status = By.cssSelector ( "body > div:nth-child(1) > div:nth-child(5) > section:nth-child(2) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6) > span:nth-child(1)");
    By CsatPage_CsatDescription = By.xpath ("//p[normalize-space()='This is an Automation test']");
    By CsatPage_NotRated = By.xpath  ("//h2[normalize-space()='Not Rated']");
    By CsatPage_CommentBox = By.xpath  ("//textarea[@placeholder='Enter comment']");
    By CsatPage_SubmitComment = By.xpath  ("//button[normalize-space()='Submit Comment']");
    By CsatPage_CommentByRCO = By.xpath  ("//*[@id=\"view-csat\"]/section/div[3]/div[2]/div/div/div/p");
    By CsatPage_Alert = By.xpath  ("//*[@id=\"body\"]/div[2]/div/div[1]/div[2]");
    //Rate on the website
    By Website_DescribeExperience = By.xpath  ("//*[@id=\"review\"]");
    By Website_RateNowButton = By.xpath  ("//button[normalize-space()='Rate Now']");
    By Website_RateIcon = By.xpath  ("//img[@alt='review icon']");
    By Website_ThankYouForRating = By.xpath  ("//span[normalize-space()='Thank you for making']");

    //Csat page on admin
    By SurveyRating = By.xpath  ("//*[@id=\"view-csat\"]/section/div[2]/div[2]/div[2]/h4");
    By SurveyRatingCustomerComment = By.xpath  ("//*[@id=\"view-csat\"]/section/div[2]/div[2]/div[2]/p[2]");

    By ResolveCsat = By.xpath("//button[normalize-space()='Resolve CSAT']");
    By ResolveCsatSuccess = By.xpath("(//div[contains(text(),'Customer satisfaction feedback has been successful')])[1]");


    //TODO: What does it do?
    public customer_feeds_webElement(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30),this);
        elementUtil = new ElementUtil(driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actionsUtilityx = new ActionsUtilityx(driver);
    }

    public customer_feeds_webElement login() throws IOException {
        javaScriptUtil.waitForPageLoad();
        doActionsSendkeys(emailField, validEmail);
        doActionsSendkeys(passwordField, validPassword);
        getElement(submitButton).click();
        return this;
    }

    public customer_feeds_webElement goto_create_feed_page() {
        getElement(SideBar_CustomerFeeds).click();
        waitForElementClickable(SideBar_AllCustomerFeeds, 10);
        isDisplayed(SideBar_AllCustomerFeeds);
        isDisplayed(SideBar_AllCustomerSatisfaction);
        getElement(SideBar_AllCustomerFeeds).click();
        return this;
    }

    public customer_feeds_webElement create_prospect_feed() throws InterruptedException {
        String em = fetchvalue("mailsacemail");
        getElement(AllCustomerFeeds_CreateFeeds).click();
        waitForElementClickable(CreateFeeds_Description, 30);
        getElement(CreateFeeds_SelectCustomerType).click();
        getElement(CreateFeeds_CustomerType_Prospect).click();
        getElement(CreateFeeds_ProspectName).sendKeys("Automation Reliance");
        getElement(CreateFeeds_ProspectEmail).sendKeys(em);
        getElement(CreateFeeds_ContactMedium).click();
        getElement(CreateFeeds_ContactMedium_Email).click();
        Thread.sleep(3000);
        waitForElementClickable(CreateFeeds_FeedType, 20);
       // getElement(CreateFeeds_FeedType).click();
        doActionsClick(CreateFeeds_FeedType);
        getElement(CreateFeeds_FeedType_Inquiry).click();
        getElement(CreateFeeds_FeedCategory).click();
        getElement(CreateFeeds_FeedCategory_Benefits).click();
        getElement(CreateFeeds_FeedLabel).click();
        getElement(CreateFeeds_FeedLabel_BenefitsConfirmation).click();
        getElement(CreateFeeds_Description).sendKeys("This is an Automation test");
        getElement(CreateFeeds_SaveFeed).click();
        Thread.sleep(3000);
        waitForElementClickable(CreateFeeds_Alert, 20);
        isDisplayed(CreateFeeds_Alert);
        try {
            String Alert = getElement(CreateFeeds_Alert).getText();
            Assert.assertEquals(Alert, "feed created successfully");
            System.out.println("Feed created successfully");
        } catch (Error e) {
            System.out.println("Prospect feed creation failed");
        }
        waitForElementClickable(AllCustomerFeeds_CreateFeeds, 20);
        String feedsLabel = getElement(FeedsTable_FeedsLabel).getText();
        Assert.assertEquals(feedsLabel, "Benefits confirmation");
        String customerType = getElement(FeedsTable_CustomerType).getText();
        Assert.assertEquals(customerType, "PROSPECT");
        String contactMedium = getElement(FeedsTable_ContactMedium).getText();
        Assert.assertEquals(contactMedium, "EMAIL");
        System.out.println("Feed was displayed on the Feeds table");
//        log.info("Created field was displayed on the fields table");
        Thread.sleep(3000);
        return this;
    }

    public customer_feeds_webElement goto_csat_page(){
        logInfo("Navigating to csat page");
        getElement(SideBar_CustomerFeeds).click();
        getElement(SideBar_AllCustomerSatisfaction).click();
        javaScriptUtil.waitForPageLoad();
        waitForElementClickable(CsatTable_EyeIcon, 20);
        getElement(CsatTable_EyeIcon).click();
        javaScriptUtil.waitForPageLoad();
        return this;
    }

    public void check_csat_isNotRated(){
        logInfo("Check csat is notRated");
        getElement(SideBar_CustomerFeeds).click();
        isDisplayed(SideBar_AllCustomerFeeds);
        isDisplayed(SideBar_AllCustomerSatisfaction);
        getElement(SideBar_AllCustomerSatisfaction).click();
        String csatStatus = getElement(CsatTable_Status).getText();
        logInfo("Check csat has OPEN status");
        Assert.assertEquals(csatStatus, "OPEN");
        waitForElementClickable(CsatTable_EyeIcon, 20);
        getElement(CsatTable_EyeIcon).click();
        waitForElementClickable(CsatPage_CsatDescription, 20);
        String csatDescription =  getElement(CsatPage_CsatDescription).getText();
        Assert.assertEquals(csatDescription, "This is an Automation test");
//        log.info("Opened csat page - the description is the same with the created feeds");
        isDisplayed(CsatPage_NotRated);
//        log.info("Not Rated is Displayed");
    }

    public void addComment2Csat (String comment){
        goto_csat_page();
        logInfo("Add Comment to CSAT");
        getElement(CsatPage_CommentBox).click();
        getElement(CsatPage_CommentBox).sendKeys(comment);
        getElement(CsatPage_SubmitComment).click();
        isDisplayed(CsatPage_Alert);
        String Alert = getElement(CsatPage_Alert).getText();
        Assert.assertEquals(Alert, "comment saved successfully");
        String commentByRCO = getElement(CsatPage_CommentByRCO).getText();
        Assert.assertEquals(commentByRCO, comment);
//        log.info("RCO was able to drop comment on csat");
    }

    public void resolveCsat(){
        goto_csat_page();
        getElement(ResolveCsat).click();
        isDisplayed(ResolveCsatSuccess);
        String resolveCsat_success = getElement(Website_ThankYouForRating).getText();
        Assert.assertEquals(resolveCsat_success, "Customer satisfaction feedback has been successfully resolved");

    }

    public customer_feeds_webElement rateCsatOnWebsite(String pageEmoji, String addCommentx) throws InterruptedException {
        String icon = getElement(Website_RateIcon).getAttribute("src");
        boolean iconState = icon.contains(pageEmoji);
        Assert.assertTrue(iconState);
        getElement(Website_DescribeExperience).sendKeys(addCommentx);
        getElement(Website_RateNowButton).click();
        Thread.sleep(2000);
        String thanks = getElement(Website_ThankYouForRating).getText();
        Assert.assertEquals(thanks, "Thank you for making");
        return this;

    }
    public customer_feeds_webElement checkCsatRating(String csatAdminStatus, String rating, String customerComment) throws InterruptedException {
        getElement(SideBar_CustomerFeeds).click();
        isDisplayed(SideBar_AllCustomerFeeds);
        isDisplayed(SideBar_AllCustomerSatisfaction);
        getElement(SideBar_AllCustomerSatisfaction).click();
        String csatStatus = getElement(CsatTable_Status).getText();
        logInfo("Check csat has " + csatAdminStatus + " Status");
        Assert.assertEquals(csatStatus, csatAdminStatus);
        waitForElementClickable(CsatTable_EyeIcon, 20);
        getElement(CsatTable_EyeIcon).click();
        logInfo("Check that CSATPage rating given by Enrollee");
        String Rating = getElement(SurveyRating).getText();
        Assert.assertEquals(Rating, rating);
        logInfo("Check that CSATPage has comment added by Enrollee");
        String CustomerComment = getElement(SurveyRatingCustomerComment).getText();
        Assert.assertEquals(CustomerComment, customerComment);
        return this;

    }








}
