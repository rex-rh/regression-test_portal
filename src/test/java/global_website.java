import Base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.global_webelement;
import utility.brokenlink;

import java.io.IOException;

import static mailsac.RestCalls.DELETE_200;
import static mailsac.RestCalls.GET_200;
import static utility.Utility.fetchvalue;

@Epic("Epic 100 -   *****************************")
@Story("Story 100 - ****************************")
public class global_website extends TestBase {
    global_webelement landingPage;
    brokenlink bl;

    @BeforeTest()
    public void init() throws InterruptedException {
        landingPage = new global_webelement(driver);
        bl = new brokenlink(driver);
    }

    @Description("Assert Home page tittle")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void landing_page() throws Exception {
        // bl.scan_link();
        try {
            landingPage.switch_to_global_website();
            Assert.fail();
            //trh.updateResultToTestRail(1, "2381", "29", "Test Passed");
        } catch (Error e) {
            System.out.println("Assert failed");
            // trh.updateResultToTestRail(5, "2381", "29", String.valueOf(e));
        }
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("An Endpoint to Get Intent 200")
    @Story("An Endpoint to Get Intent 200")
    public void email_validation() throws InterruptedException, IOException {
        String em = fetchvalue("mailsacemail");

        /**
         * Getting email body -
         */
        Response response = GET_200("/addresses/" + em + "/messages");
        response.prettyPeek().then().spec(responseSpec_200);

        /**
         * Storing some crutial response body in a string
         */
        String firstlink = response.path("[0].links[0]");
        String secondtlink = response.path("[1].links[0]");
        String thirdlink = response.path("[2].links[0]");

        String msgid = response.path("[0]._id");
        String senderemail = response.path("[0].from[0].address");
        String sendername = response.path("[0].from[0].name");
        String subject = response.path("[0].subject");

        /**
         * Asserting response body
         */
        /*
        Assert.assertNotNull(firstlink, "the url attached to the email is null");
        Assert.assertEquals("", senderemail,"Senders Emails is not valid");
        Assert.assertEquals("", sendername,"Senders name is not valid");
        Assert.assertEquals("", subject,"Failed to validate Email subject");

         */

        System.out.println(firstlink);


        /**
         * Deleting inbox after Validation has been completed
         */
        Response respons = DELETE_200("/addresses/" + em + "/messages/" + msgid);
        respons.prettyPeek();

        /**
         * Getting the new URl attached to the email.
         */
        driver.get(firstlink);
    }
}
