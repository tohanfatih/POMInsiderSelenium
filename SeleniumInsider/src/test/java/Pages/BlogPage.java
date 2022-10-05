package Pages;

import org.openqa.selenium.By;

import static InsiderDemo.DemoTests.driver;

public class BlogPage {

    public static void seeFindJob(){
        driver.findElement(By.xpath("(//a[text()='Find your dream job'])[1]")).isDisplayed();
    }

    public static void clickFindJob(){
        driver.findElement(By.xpath("(//a[text()='Find your dream job'])[1]")).click();
    }

}
