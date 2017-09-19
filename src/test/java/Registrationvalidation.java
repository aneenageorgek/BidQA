import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Loginpage;
import pages.Registration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Registrationvalidation
{
    WebDriver driver;
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

    //T129188:Verify "Register Now" in login page, will redirect user to the registration page.
    //Expected result:User should be redirected to registration page.
    //Initialize the login page
    @BeforeMethod
    public void init() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Loginpage page = new Loginpage(driver);
        page.createAccount();
    }

    //T129370:Verify user cannot register with blank "First" name field
    //Expected result:User cannot create an account.User receives error message: "This value is required".

    //T129371:Verify user cannot register with blank "Last" name field
    //Expected result:User cannot create an account.User receives error message: "This value is required".

    //T129372:Verify user cannot register with blank "Address" field.
    //Expected result:User cannot create an account.User receives error message: "This value is required".

    //T129601:Verify user cannot register with blank "Email" field.
    //Expected result:User cannot create an account.User receives error message: "This value is required".

    //T129373:Verify user cannot register with blank "Phone" number field (completed)
    //Expected result:User cannot create an account.User receives error message: "This value is required".

    //T129187:Verify user can not register with blank "Password" field
    //Expected result:User cannot create an account.User receives error message: "This value is required".

    //T129603:Verify user cannot register with blank "Retype" password
    //Expected result:User cannot create an account.User receives error message: "This value is required".

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
        Assert.assertEquals(reg.firstNameAlert(), "This value is required.");
        Assert.assertEquals(reg.lastNameAlert(), "This value is required.");
        Assert.assertEquals(reg.adressAlert(), "This value is required.");
        Assert.assertEquals(reg.phoneAlert(), "This value is required.");
        Assert.assertEquals(reg.emailAlert(), "This value is required.");
        Assert.assertEquals(reg.passwordAlert(), "This value is required.");
        Assert.assertEquals(reg.rePasswordAlert(), "This value is required.");
    }


    @AfterClass
    //initialize to close the driver

    public void finish() {
        driver.close();
    }


    }
