package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class TalentPage {
    @FindBy(xpath="//*[@id=\"firstName\"]")
    WebElement firstname;
    @FindBy(xpath="//*[@id=\"lastName\"]")
    WebElement lastname;
    @FindBy(xpath="//*[@id=\"county\"]")
    WebElement country;
    @FindBy(xpath="//*[@id=\"address\"]")
    WebElement adress;
    @FindBy(xpath="//*[@id=\"phone\"]")
    WebElement phone;
    @FindBy(xpath = "/html/body/div[2]/p/label[1]")
    WebElement talent;
    @FindBy(xpath = "/html/body/div[2]/p/label[2]")
    WebElement yourself;
    @FindBy(xpath="/html/body/div[2]/div[7]/div/button")
    WebElement okButtonInDialog;
    @FindBy(xpath = "/html/body/app/ui-view/public-area/div/header/nav/div[2]/ul[2]/li[2]/a")
    WebElement profileIcon;
    @FindBy(xpath = "/html/body/app/ui-view/public-area/div/header/nav/div[2]/ul[2]/li[2]/ul/li/div/a[2]/div/div[2]/p")
    WebElement logout;
    @FindBy(xpath = "/html/body/app/ui-view/public-area/div/header/nav/div[2]/ul[2]/li[2]/ul/li/div/a[1]/div/div[2]/p")
    WebElement myprofile;
    WebDriver driver;

    public TalentPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver, this);
    }

    public void talentClick() {
        talent.click();
    }

    public void logout(){
        profileIcon.click();
        logout.click();
    }
    public void myprofile(){
        profileIcon.click();
        myprofile.click();
    }
    public String  checkfirstname() {

        return firstname.getAttribute("value");

    }
    public String checkLastname() {
       return lastname.getAttribute("value");
    }
    public String checkCountry() {
        return country.getAttribute("value");
    }
    public String checkAdress() {
         return adress.getAttribute("value");
    }
    public String checkPhone() {
        return phone.getAttribute("value");
    }
    public void clickYourselfRadio() {
        yourself.click();
    }

    public void clickOkInDialog() {
        okButtonInDialog.click();
    }
}
