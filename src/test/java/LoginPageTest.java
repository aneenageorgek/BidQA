
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Loginpage;
import pages.TalentPage;
import pages.TestConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    WebDriver driver;

    //Initialize the driver
    @BeforeClass
    public void init() {
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
    }

    //Page setup before going to Login
    @BeforeMethod
    public  void setup() {
        driver.get("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
    }

    //T129716:Verify user can Login in with valid Email and Password combination.
    //Expected Result:User should successfully login to user account.User should be directed to the Talents home page.
    //User successful Login using Dataprovider.
    @Test(dataProviderClass = Dataprovider.class,
            dataProvider= "LoginTestDataProvider", enabled=true, description="Login",groups={"Smoke"},priority=1)
    public void testWithDataProvider(String username, String password) {
        //create instance of login page
        Loginpage log = new Loginpage(driver, username, password);
        log.clickLogin();
        TalentPage tal=new TalentPage(driver);
        tal.logout();
    }

    //T129170:Verify user can not login with an invalid password.
    //Expected Result:User should get an error message indicating "Invalid email or password" and not be able to login.
    //User cannot login with valid username and invalid password
    @Test(priority = 2)
    public void testInvalidPassword() throws InterruptedException {
        //Creating instance of loginPage
        Loginpage log = new Loginpage(driver,TestConstants.availableEmail, TestConstants.invalidpassword);
        //Add the wrong password
        log.clickLogin();
        //wait for locating the error message
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(log.getLoginAlert(), "Invalid Email or Password.");
        System.out.println("Unregistered user");
    }

    //T129717:Verify user cannot login with blank "Password" field:
    //Expected Result:User cannot log in to user account.User receives error message: "This value is required".
    // User login with valid username blank password
    @Test(priority = 3)
    public void testBlankPassword() {
        //Creating instance of loginPage
        Loginpage log = new Loginpage(driver,TestConstants.blankemail, TestConstants.password1);
        log.clickLogin();
        //wait for sign-in url
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(log.blankpassAlert(), "This value is required.");
        System.out.println("Unregistered user");
    }

    //T129166:Verify user cannot login with blank "Email" field.
    //Expected Result:User receives error message below the email field:"This value is required".
    //User login with blank username and valid password
    @Test(priority = 4)
    public void testBlankEmail() {
        //Creating instance of loginPage
        Loginpage log = new Loginpage(driver,TestConstants.blankemail, TestConstants.password);
        //wait for sign-in url
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(log.blankUsernameAlert(), "This value is required.");
        System.out.println("Unregistered user");
    }

    //T129169:Verify user cannot login with an invalid email address.
    //Expected Result:User should get an error message indicating "Invalid email or password" and not be able to login.
    //User Login with invalid username and valid password
    @Test(priority = 5)
    public void testInvalidEmail() throws InterruptedException {
        //Creating instance of loginPage
        Loginpage log = new Loginpage(driver,TestConstants.email, TestConstants.password);
        log.clickLogin();
        //wait for locating error message
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(log.userAlert(), "Invalid Email or Password.");
        System.out.println("Unregistered user");
    }

    //user cannot login with invalid username and invalid password
    @Test(priority = 6)
    public void testInvalidEmailPassword() throws InterruptedException {
        //Creating instance of loginPage
        Loginpage log = new Loginpage(driver,TestConstants.email, TestConstants.invalidpassword);
        log.clickLogin();
        //wait for locating error message
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(log.userAlert(), "Invalid Email or Password.");
        System.out.println("Unregistered user");
    }

    //User cannot login with blank username and blank password.
    @Test(priority = 7)
    public void testBlankEmailPassword() {
        //Creating instance of loginPage
        Loginpage log = new Loginpage(driver,TestConstants.blankemail, TestConstants.password1);
        log.clickLogin();
        //wait for sign-in url
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com/sign-in.html"));
        Assert.assertEquals(log.blankUsernameAlert(), "This value is required.");
        Assert.assertEquals(log.blankpassAlert(), "This value is required.");
        System.out.println("Unregistered user");
    }
    
    //Close the driver
    @AfterClass
    public void finish()
    {
        driver.close();

    }


}

