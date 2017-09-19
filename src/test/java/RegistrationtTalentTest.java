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

public class RegistrationtTalentTest {
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

    /*@AfterMethod
    //Initailize the logout page
    public void logout() {
        TalentPage talentPage = new TalentPage(driver);
        talentPage.logout();
    }*/
    //T129164:Verify user can create account after entering valid credentials in all required fields.
    //Expected Result:User successfully creates account.User is redirected to the main (Sign In) page.
    //Talent Registration using Dataprovider
    @Test(dataProviderClass = DataproviderRegistration.class,
            dataProvider = "RegistrationTestDataProvider", enabled = true, description = "Login", groups = {"Smoke"}, priority = 1)
    public void testWithDataProvider(String firstname, String middlename, String lastname, String countryname,
                                     String address, String phone,
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
        reg.createaccount();

        //checking for firstname field is empty

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html");
        Loginpage log1 = new Loginpage(driver, currentEmail, currentPassword);
        log1.clickLogin();
        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talents.html"));
        TalentPage tal = new TalentPage(driver);
        tal.talentClick();
        tal.clickOkInDialog();
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talents.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/talents.html");
    }

    @Test(dataProviderClass = DataproviderassertRegister.class,
            dataProvider = "RegistrationTesProvider", enabled = true, description = "Login", groups = {"Smoke"}, priority = 1)
    public void testWithDataProviderassert(String firstname, String middlename, String lastname, String countryname,
                                           String address, String phone,
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
        reg.createaccount();

        //T129370:Verify user cannot register with blank "First" name field
        //Expected result:User cannot create an account.User receives error message: "This value is required".
        Assert.assertEquals(reg.firstNameAlert(), "This value is required.");
        //T129371:Verify user cannot register with blank "Last" name field
        //Expected result:User cannot create an account.User receives error message: "This value is required".
        Assert.assertEquals(reg.lastNameAlert(), "This value is required.");
        //T129372:Verify user cannot register with blank "Address" field.
        //Expected result:User cannot create an account.User receives error message: "This value is required".
        Assert.assertEquals(reg.adressAlert(), "This value is required.");
        //T129601:Verify user cannot register with blank "Email" field.
        //Expected result:User cannot create an account.User receives error message: "This value is required".
        Assert.assertEquals(reg.phoneAlert(), "This value is required.");
        //T129373:Verify user cannot register with blank "Phone" number field (completed)
        //Expected result:User cannot create an account.User receives error message: "This value is required".
        Assert.assertEquals(reg.emailAlert(), "This value is required.");
        //T129187:Verify user can not register with blank "Password" field
        //Expected result:User cannot create an account.User receives error message: "This value is required".
        Assert.assertEquals(reg.passwordAlert(), "This value is required.");
        //T129603:Verify user cannot register with blank "Retype" password
        //Expected result:User cannot create an account.User receives error message: "This value is required".
        Assert.assertEquals(reg.rePasswordAlert(), "This value is required.");
    }


    @AfterClass
    //initialize to close the driver

    public void finish() {
        driver.close();
    }
}



