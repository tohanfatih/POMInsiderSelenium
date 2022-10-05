package Pages;

import InsiderDemo.DemoTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends DemoTests {

    @FindBy(xpath = "//a[@id='wt-cli-accept-all-btn']")
    static
    WebElement acceptCookies;

    @FindBy(xpath = "//a[contains(text(),'Contacts')]")
    WebElement contactsLink;

    @FindBy(xpath = "//a[contains(text(),'New Contact')]")
    WebElement newContactLink;


    @FindBy(xpath = "//a[contains(text(),'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath = "//a[contains(text(),'Tasks')]")
    WebElement tasksLink;

    public static String verifyHomePageTitle(){
        return driver.getTitle();
    }



    public static void clickMore(){
        driver.findElement(By.xpath("//span[text()='More']")).click();
    }

    public static void clickCareers(){
        driver.findElement(By.xpath("//h5[text()='Careers']")).click();
    }

    public static void clickAcceptCookies(){
        driver.findElement(By.xpath("//a[@id='wt-cli-accept-all-btn']")).click();
    }
/*
    public ContactsPage clickOnContactsLink(){
        contactsLink.click();
        return new ContactsPage();
    }

    public DealsPage clickOnDealsLink(){
        dealsLink.click();
        return new DealsPage();
    }

    public TasksPage clickOnTasksLink(){
        tasksLink.click();
        return new TasksPage();
    }*/

    public void clickOnNewContactLink(){
        Actions action = new Actions(driver);
        action.moveToElement(contactsLink).build().perform();
        newContactLink.click();

    }

}
