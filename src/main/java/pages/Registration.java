package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Registration {

    WebDriver driver;

    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void setMiddleName(String middleName) {
        this.middleName.sendKeys(middleName);
    }

    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void setCountry(String country) {
        this.country.sendKeys(country);
    }

    public void setAddress(String address) {
        this.address.sendKeys(address);
    }

    public void setPhone(String phone) {
        this.phone.sendKeys(phone);
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail.sendKeys(registerEmail);
    }


    public void setRegisterPassword(String registerPassword) {
        this.registerPassword.sendKeys(registerPassword);
    }

    public void setRegisterRePassword(String password){
        registerRePassword.sendKeys(password);
    }
    public String firstNameAlert()
    {
     return firstnameAlert.getText();
    }
    public  String lastNameAlert()
    {
     return lastNameAlert.getText();
    }
    public String adressAlert()
    {
        return adressAlert.getText();
    }

    public String phoneAlert()
    {
        return phoneAlert.getText();
    }
    public String emailAlert()
    {
        return emailAlert.getText();
    }
    public String passwordAlert()
    {
        return passwordAlert.getText();
    }
    public String rePasswordAlert()
    {
        return rePasswordAlert.getText();
    }
    //*[@id="registerForm"]/div[3]/div[2]/div/span[1]
    @FindBy(xpath = "//*[@id=\"registerForm\"]/div[3]/div[2]/div/span[1]")
    WebElement rePasswordAlert;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/div[3]/div[1]/div/span[1]")
    WebElement passwordAlert;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/div[2]/div/div[2]/div[4]/div/span[2]")
    WebElement emailAlert;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/div[2]/div/div[2]/div[3]/div/span[1]")
    WebElement phoneAlert;
    @FindBy(xpath="//*[@id=\"registerForm\"]/div[2]/div/div[2]/div[2]/div/span[1]")
    WebElement adressAlert;
    @FindBy(xpath="//*[@id=\"registerForm\"]/div[2]/div/div[1]/div[3]/div/span[1]")
    WebElement lastNameAlert;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/div[2]/div/div[1]/div[1]/div/span[1]")
    WebElement firstnameAlert;
    @FindBy(id = "signUpFirstName")
    WebElement firstName;
    @FindBy(id = "signUpmiddleName")
    WebElement middleName;
    @FindBy(id = "signUpLastName")
    WebElement lastName;
    @FindBy(id = "signUpCounty")
    WebElement country;
    @FindBy(id = "signUpAddress")
    WebElement address;
    @FindBy(id = "signUpPhone")
    WebElement phone;
    @FindBy(id = "signUpRegisterEmail")
    WebElement registerEmail;
    @FindBy(id = "signUpRegisterPassword")
    WebElement registerPassword;
    @FindBy(id = "signUpRegisterRePassword")
    WebElement registerRePassword;
    @FindBy(xpath = "//*[@id=\"createAccount\"]")
    WebElement createAccount;
    @FindBy(xpath = "//*[@id=\"userType\"]")
    WebElement scout;

    public Registration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }




    /*public Registration(WebDriver driver, String firstname,
                        String middlename, String lastname,String countryname,
                        String adress, String phoneno,
                        String registeremail, String registerpassword,
                        String registerrepassword)

    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        firstName.sendKeys(firstname);
        middleName.sendKeys(middlename);
        lastName.sendKeys(lastname);
        country.sendKeys(countryname);
        address.sendKeys(adress);
        phone.sendKeys(phoneno);
        registerEmail.sendKeys(registeremail);
        registerPassword.sendKeys(registerpassword);
        registerRePassword.sendKeys(registerrepassword);
    }*/

    public void createScoutaccount() {
        scout.click();
    }


    public void createaccount() {
        createAccount.click();
    }


}






