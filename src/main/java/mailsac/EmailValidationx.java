package mailsac;

import Base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;

import static mailsac.RestCalls.DELETE_200;
import static mailsac.RestCalls.GET_200;
import static utility.Utility.fetchvalue;

public class EmailValidationx extends TestBase {
    static String msgid;
    static String senderEmail;
    static String senderName;
    static String subject;
    static String em;
    static Response response;



    public static void read_email() throws InterruptedException {
        em = fetchvalue("mailsacemail");
        //Getting email body -
        response = GET_200("/addresses/" + em + "/messages");
        response.prettyPeek().then().spec(responseSpec_200);

       //Storing some crutial response body in a string
//        firstLink = response.path("[0].links[0]");
//        secondLink = response.path("[0].links[1]");
//        thirdLink = response.path("[2].links[0]");
        msgid = response.path("[0]._id");
        senderEmail = response.path("[0].from[0].address");
        senderName = response.path("[0].from[0].name");
        subject = response.path("[0].subject");

        // * Asserting response body

//        Assert.assertNotNull(firstlink, "the url attached to the email is null");
//        Assert.assertEquals("", senderemail,"Senders Emails is not valid");
//        Assert.assertEquals("", sendername,"Senders name is not valid");
//        Assert.assertEquals("", subject,"Failed to validate Email subject");
//
    }
    public static void getLinkFromEmail( String path, String contains) throws InterruptedException {
        String link = response.path(path);
        driver.get(link);
        System.out.println("THIS IS FROM THE TEST HERE " + link);
        String currentUrl = driver.getCurrentUrl();
        boolean x = currentUrl.contains(contains);
        Assert.assertTrue(x);
        System.out.println(contains + " Was in the page's URL");
    }
    public static void delete_email() throws InterruptedException {
        // * Deleting inbox after Validation has been completed
        Response respons = DELETE_200("/addresses/" + em + "/messages/" + msgid);
        respons.prettyPeek();
    }





}
