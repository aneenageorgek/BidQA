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






