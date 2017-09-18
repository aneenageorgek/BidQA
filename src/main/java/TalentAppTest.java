import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import pages.TestConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TalentAppTest {
    @Test
    public void login_precondition() {
        //prevent save password
        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //Webdriver object creation
        WebDriver driver = new ChromeDriver(capabilities);

        //Go to URL
        driver.get("http://52.53.181.39/");

        //implicit wait in each steps
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Enter the invalid_email in login page
        //driver.findElement(By.id("loginEmail")).sendKeys(TestConstants.email);

        //Enter the password in the login page
        driver.findElement(By.id("password")).sendKeys(TestConstants.password);

        //Click the login button
        driver.findElement(By.id("login")).click();

        //Check error message is displayed or not
        WebElement text = driver.findElement(By.id("loginAlert"));
        if (text.getText().equals("Invalid Email or Password.")) {
            System.out.println("User is not Registered");
        } else {
            System.out.println("Registered User");
        }

    }

    @Test
    public void testLogin_Logout() {
        //prevent save password
        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //Webdriver object creation
        WebDriver driver = new ChromeDriver(capabilities);

        //Go to URL
        driver.get("http://52.53.181.39/");

        //implicit wait in each steps
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Enter valid email in login page
        //driver.findElement(By.id("loginEmail")).sendKeys(TestConstants.availableEmail);

        //Enter valid password in login page
        driver.findElement(By.id("password")).sendKeys(TestConstants.password);

        //Click on login button
        driver.findElement(By.id("login")).click();

        //click on profile icon on the right side of the header of page
        driver.findElement(By.xpath("/html/body/div/header/nav/div[2]/ul[2]/li[2]/a")).click();

        //click on logout option from the menu
        driver.findElement(By.xpath("/html/body/div/header/nav/div[2]/ul[2]/li[2]/ul/li/div/a[2]/div/div[2]/p")).click();
    }

    @Test
    public void My_profile() {
        //prevent save password
        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //Webdriver object creation
        WebDriver driver = new ChromeDriver(capabilities);

        //Go to URL
        driver.get("http://52.53.181.39/");

        //implicit wait in each steps
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Enter valid email in login page
        driver.findElement(By.id("loginEmail")).sendKeys(TestConstants.availableEmail);

        //Enter valid password in login page
        driver.findElement(By.id("password")).sendKeys(TestConstants.password);

        //Click on login button
        driver.findElement(By.id("login")).click();
        //click on top right icon
        driver.findElement(By.xpath("/html/body/div/header/nav/div[2]/ul[2]/li[2]/a")).click();

        //click on my profile,it direct to the profile details
        driver.findElement(By.xpath("/html/body/div/header/nav/div[2]/ul[2]/li[2]/ul/li/div/a[1]/div/div[2]/p")).click();


    }

    @Test
    public void Testregistration() {
        //Save password
        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //Create object in web driver
        WebDriver driver = new ChromeDriver(capabilities);

        //set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Go to URL
        driver.get("http://52.53.181.39/");

        //Click on register button in login page
        //driver.findElement(By.id("register")).click();

        //Enter first name for registration
        driver.findElement(By.id("firstName")).sendKeys(TestConstants.firstName);

        //Enter middle name for registration
        driver.findElement(By.id("middleName")).sendKeys(TestConstants.middleName);

        //Enter last name for registration
        driver.findElement(By.id("lastName")).sendKeys(TestConstants.lastName);

        // country is automatically selected as USA for registration
        //driver.findElement(By.id("country")).sendKeys("USA");

        //Enter address for registration
        driver.findElement(By.id("address")).sendKeys(TestConstants.address);

        //Enter phone no for registration
        driver.findElement(By.id("phone")).sendKeys(TestConstants.phone);

        //generate random email and save in to a string email
        String email = TestConstants.email;

        //Enter the email for registration
        driver.findElement(By.id("registerEmail")).sendKeys(email);

        //Enter the password for registration
        driver.findElement(By.id("registerPassword")).sendKeys(TestConstants.password);

        //Retype the password for registration
        driver.findElement(By.id("registerRePassword")).sendKeys(TestConstants.password);

        //Click on create on account button,page redirect to login page
        driver.findElement(By.id("createAccount")).click();

        //Enter the email in login page
        driver.findElement(By.id("loginEmail")).sendKeys(email);

        //Enter the password in login page
        driver.findElement(By.id("password")).sendKeys(TestConstants.password);

        //Click on login button
        driver.findElement(By.id("login")).click();

        //select other talent radiobutton
        //driver.findElement(By.xpath("/html/body/div[3]/p/label[1]")).click();

        //select yourself radio button
        driver.findElement(By.xpath("/html/body/div[3]/p/label[2]")).click();

        //click the button on the selection window
        driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();


    }
}

    //@Test
   /* public void AddNew_Talent() {

        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        //Create object in web driver
        WebDriver driver = new ChromeDriver(capabilities);

        //set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Go to URL
        driver.get("http://52.53.181.39/");

        //Click on register button in login page
        driver.findElement(By.id("register")).click();

        //Enter first name for registration
        driver.findElement(By.id("firstName")).sendKeys(TestConstants.firstName);

        //Enter middle name for registration
        driver.findElement(By.id("middleName")).sendKeys(TestConstants.middleName);

        //Enter last name for registration
        driver.findElement(By.id("lastName")).sendKeys(TestConstants.lastName);

        // country is automatically selected as USA for registration
        //driver.findElement(By.id("country")).sendKeys("USA");

        //Enter address for registration
        driver.findElement(By.id("address")).sendKeys(TestConstants.address);

        //Enter phone no for registration
        driver.findElement(By.id("phone")).sendKeys(TestConstants.phone);

        //generate random email and save in to a string email
        String email = TestConstants.email;

        //Enter the email for registration
        driver.findElement(By.id("registerEmail")).sendKeys(email);

        //Enter the password for registration
        driver.findElement(By.id("registerPassword")).sendKeys(TestConstants.password);

        //Retype the password for registration
        driver.findElement(By.id("registerRePassword")).sendKeys(TestConstants.password);

        //Click on create on account button,page redirect to login page
        driver.findElement(By.id("createAccount")).click();

        //Enter the email in login page
        driver.findElement(By.id("loginEmail")).sendKeys(email);

        //Enter the password in login page
        driver.findElement(By.id("password")).sendKeys(TestConstants.password);

        //Click on login button
        driver.findElement(By.id("login")).click();

        //select other talent radiobutton
        //driver.findElement(By.xpath("/html/body/div[3]/p/label[1]")).click();

        //select yourself radio button
        driver.findElement(By.xpath("/html/body/div[3]/p/label[2]")).click();

        //click the button on the selection window
        driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();

        //click on new tab
        driver.findElement(By.id("new")).click();
        //click on personal tab
        driver.findElement(By.id("steps-uid-0-t-0")).click();

        //Enter firstname
        driver.findElement(By.id("firstName")).sendKeys(TestConstants.newfirstname);
        //Enter middlename
        driver.findElement(By.id("middleName")).sendKeys(TestConstants.newmiddlename);
        //Enter lastname
        driver.findElement(By.id("lastName")).sendKeys(TestConstants.lastName);
        //choose a file

        //driver.findElement(By.xpath("//*[@id=\"steps-uid-0-p-0\"]/div/div/div[2]/div[1]/div[1]/div/input"));
        driver.findElement(By.xpath("//*[@id=\"steps-uid-0-p-0\"]/div/div/div[2]/div[1]/div[1]/div/span/label/span[2]")).sendKeys("/Users/roshan/Desktop/IMG_3553_PNG.png");

    }*/






