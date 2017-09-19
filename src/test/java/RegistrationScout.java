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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RegistrationScout {
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

    //Initialize the login page
    @BeforeMethod
    public void init() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Loginpage page = new Loginpage(driver);
        page.createAccount();
    }
   /*( @Test
    public void scoutRegistration() {
        Registration newScout = new Registration(driver);
        newScout.createScoutaccount();
        //WebDriverWait waitForClick=new WebDriverWait(driver,10);
    }*/

   //T129164:Verify user can create account after entering valid credentials in all required fields.
   //Expected Result:User successfully creates account.User is redirected to the main (Sign In) page.
   //Scout registration using dataprovider
    @Test(dataProviderClass = DataproviderRegistration.class,
            dataProvider= "RegistrationTestDataProvider", enabled=true, description="Login",groups={"Smoke"},priority=1)
    public void testWithDataProvider( String firstname,String middlename,String lastname,String countryname,
                                      String address,String phone,
                                      String currentEmail,
                                      String currentPassword) {
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
        reg.createScoutaccount();
        reg.createaccount();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html");
        Loginpage log1 = new Loginpage(driver, currentEmail, currentPassword);
        log1.clickLogin();
        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talents.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talents.html");
    }

    @AfterMethod
    //Initailize the logout page
    public void logout() {
        TalentPage talentPage = new TalentPage(driver);
        talentPage.logout();
    }

    @AfterClass
    public void finish(){
        driver.close();
    }

}
