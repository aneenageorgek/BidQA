import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Loginpage;
import pages.Registration;
import pages.TalentPage;
import pages.TestConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RegistrationYourself {
    WebDriver driver;

    //Initailize the driver
    @BeforeClass
    public void setup() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/");
     }


    @BeforeMethod
    //Initialize the login page
    public void init() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Loginpage page = new Loginpage(driver);
        page.createAccount();
    }

    @AfterMethod
    //Initailize the logout page
    public void logout() {
        TalentPage talentPage = new TalentPage(driver);
        talentPage.logout();
    }
    //T129164:Verify user can create account after entering valid credentials in all required fields.
    //Expected Result:User successfully creates account.User is redirected to the main (Sign In) page.
    //Yourself Registration using Dataprovider
    @Test(dataProviderClass = DataproviderYours.class, dataProvider= "RegistrationTestDataProvideryours",
            enabled=true, description="Login",groups={"Smoke"},priority=1)
    public void testWithDataProvideryours( String firstname,String middlename,
                                           String lastname,String countryname,
                                           String address,String phone,
                                           String currentEmail,
                                           String currentPassword) throws InterruptedException {

        Registration reg = new Registration(driver);
        reg.setFirstName(firstname);
        reg.setMiddleName(middlename);
        reg.setLastName(lastname);
        reg.setCountry(countryname);
        reg.setAddress(address);
        reg.setPhone(phone);
        reg.setRegisterEmail(currentEmail);
        reg.setRegisterPassword(currentPassword);
        reg.setRegisterRePassword(currentPassword);
        //Thread.sleep(10000);
        reg.createaccount();

        WebDriverWait wait3 = new WebDriverWait(driver, 10);
        wait3.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));

        Assert.assertEquals(driver.getCurrentUrl(), "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html");
        Loginpage log = new Loginpage(driver, currentEmail, currentPassword);
        //log.setEmailAddress(TestConstants.email);
        //log.setPassWord(TestConstants.password);
        log.clickLogin();
        WebDriverWait wait4 = new WebDriverWait(driver, 10);
        wait4.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talents.html"));
        TalentPage yourself = new TalentPage(driver);
        yourself.clickYourselfRadio();
        WebDriverWait waitAfterRadioClick = new WebDriverWait(driver, 10);
        yourself.clickOkInDialog();
        WebDriverWait waitFornextPage = new WebDriverWait(driver, 20);
        waitFornextPage.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talent.html?step=1"));
        String actual = driver.getCurrentUrl();
        //Assert.assertEquals(actual, "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talent.html?step=2&id=1566");
        Assert.assertTrue(actual.contains("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talent.html?step=1"));

    }
    @AfterClass
    public void finish() {
        //driver.close();
    }
}

