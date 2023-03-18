package Portals;

import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.portals_elements.customer_feeds_webElement;
import utility.brokenlink;
import java.io.IOException;

import static Helper.ActionsUtilityx.*;
import static Helper.ActionsUtilityx.switch_tabs;
import static Listeners.ExtentReport.logInfo;
import static mailsac.EmailValidationx.*;

public class Customer_feeds_test extends TestBase {

    customer_feeds_webElement csat;
    brokenlink bl;

    //TODO: What does it do?
    @BeforeTest()
    public void init() throws InterruptedException {
        csat = new customer_feeds_webElement(driver);
         bl = new brokenlink(driver);
    }


    @Description("csat")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void create_feed() throws Exception {
        try {
            logInfo("Login to Admin"); csat.login();
            logInfo("Go to Create feeds page"); csat.goto_create_feed_page();
            logInfo("Create Prospect feed"); csat.create_prospect_feed();
            trh.updateResultToTestRail(1, "2408", "95", "Test Passed");
        } catch (
                Error |
                Exception e) {
            System.out.println("Assert failed");
            trh.updateResultToTestRail(5, "2408", "95", String.valueOf(e));
            Assert.fail();
        }

    }

    @Test(priority = 2)
    public void validated_email_link() throws Exception {
        try{
            read_email(); delete_email(); openNewtab();
            getLinkFromEmail("[0].links[0]","score=ONE");
            trh.updateResultToTestRail(1, "3031", "95", "Test Passed");
        }catch (Error |
                Exception e){
            System.out.println("Email Validation failed");
            trh.updateResultToTestRail(5, "3031", "95", String.valueOf(e));
            Assert.fail("Test failed");
        }
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void csatNotRated(){
        try{
            switch_tabs(0);
            csat.check_csat_isNotRated();
        }catch (Error e){
            System.out.println("Comment ISNOT RATED failed");
            Assert.fail();
        }

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void commentOnFeeds() throws Exception {
        try{
            csat.addComment2Csat("Comment by Automation");
            trh.updateResultToTestRail(1, "2437", "95", "Test Passed");
        }catch (Error |
                Exception e){
            System.out.println("Comment on Csat Test failed");
            trh.updateResultToTestRail(5, "2437", "95", String.valueOf(e));
            Assert.fail();
        }
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5)
    public void resolveCsatCheck() throws Exception {
        try{
            csat.resolveCsat();
            trh.updateResultToTestRail(1, "2414", "95", "Test Passed");
        }catch (Error |
                Exception e){
            System.out.println("Resolve CSAT Test failed");
            trh.updateResultToTestRail(5, "2414", "95", String.valueOf(e));
            Assert.fail();
        }
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6)
    public void csatBadRating() throws Exception {
        try{
            switch_tabs(1);
            logInfo("Add comment to bad review on the website");
            csat.rateCsatOnWebsite("img/terrible.svg", "RATING ONE Comment from Automation");
            closeTab();
            switch_tabs(0);
            logInfo("Verify RATING ONE  comment on csat survey page");
            csat.checkCsatRating("ESCALATED","Terrible Encounter", "RATING ONE Comment from Automation");
            trh.updateResultToTestRail(1, "3029", "95", "Test Passed");
        }catch (Error |
                Exception e){
            System.out.println("Comment ONE on Csat Test failed");
            trh.updateResultToTestRail(1, "3029", "95", "Test Passed");
            Assert.fail();
        }
    }

    @Test(priority = 7)
    public void csatRateProspectFeedsTwo() throws InterruptedException {
        try{
            logInfo("Go to Create feeds page"); csat.goto_create_feed_page();
            logInfo("Create Prospect feed"); csat.create_prospect_feed();
            read_email(); delete_email(); openNewtab();;
            getLinkFromEmail("[0].links[1]","score=TWO");
            csat.rateCsatOnWebsite("img/bad.svg", "RATING TWO Comment from Automation");
            closeTab();
            switch_tabs(0);
            logInfo("Verify RATING TWO comment on csat survey page");
            csat.checkCsatRating("ESCALATED","Poor Encounter", "RATING TWO Comment from Automation");
        }catch (Error e){
            System.out.println("Comment TWO on Csat Test failed");
            Assert.fail();
        }
    }

    @Test(priority = 8)
    public void csatRateProspectFeedsThree() throws InterruptedException {
        try{
            logInfo("Go to Create feeds page"); csat.goto_create_feed_page();
            logInfo("Create Prospect feed"); csat.create_prospect_feed();
            read_email(); delete_email(); openNewtab();;
            getLinkFromEmail("[0].links[2]","score=THREE");
            csat.rateCsatOnWebsite("img/fair-icon.svg", "RATING THREE Comment from Automation");
            closeTab();
            switch_tabs(0);
            logInfo("Verify RATING THREE comment on csat survey page");
            csat.checkCsatRating("CLOSED","Fair Encounter", "RATING THREE Comment from Automation");
        }catch (Error e){
            System.out.println("Comment THREE on Csat Test failed");
            Assert.fail();
        }
    }
    @Test(priority = 9)
    public void csatRateProspectFeedsFOUR() throws InterruptedException {
        try{
            logInfo("Go to Create feeds page"); csat.goto_create_feed_page();
            logInfo("Create Prospect feed"); csat.create_prospect_feed();
            read_email(); delete_email(); openNewtab();;
            getLinkFromEmail("[0].links[3]","score=FOUR");
            csat.rateCsatOnWebsite("img/good-icon.svg", "RATING FOUR Comment from Automation");
            closeTab();
            switch_tabs(0);
            logInfo("Verify RATING FOUR comment on csat survey page");
            csat.checkCsatRating("CLOSED","Good Encounter", "RATING FOUR Comment from Automation");
        }catch (Error e){
            System.out.println("Comment FOUR on Csat Test failed");
            Assert.fail();
        }
    }

    @Test(priority = 10)
    public void csatRateProspectFeedsFive() throws InterruptedException {
        try{
            logInfo("Go to Create feeds page"); csat.goto_create_feed_page();
            logInfo("Create Prospect feed"); csat.create_prospect_feed();
            read_email(); delete_email(); openNewtab();;
            getLinkFromEmail("[0].links[4]","score=FIVE");
            csat.rateCsatOnWebsite("img/great.svg", "RATING FIVE Comment from Automation");
            closeTab();
            switch_tabs(0);
            logInfo("Verify RATING FIVE comment on csat survey page");
            csat.checkCsatRating("CLOSED","Excellent Encounter", "RATING FIVE Comment from Automation");
        }catch (Error e){
            System.out.println("Comment FIVE on Csat Test failed");
            Assert.fail();
        }
    }



}
