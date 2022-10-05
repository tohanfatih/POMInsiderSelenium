package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static InsiderDemo.DemoTests.driver;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class JobSearchPage {

    public static void jobFilterByLocation() throws InterruptedException {
        driver.findElement(By.xpath("//label[@for='filter-by-location']")).isDisplayed();
        sleep(2000);
        driver.findElement(By.xpath("//span[@id='select2-filter-by-location-container']")).click();
        driver.findElement(By.xpath("//li[text()='Istanbul, Turkey']")).click();
        sleep(2000);
    }

    public static void jobFilterByDepartment() throws InterruptedException {
        driver.findElement(By.xpath("//span[@id='select2-filter-by-department-container']")).click();
        driver.findElement(By.xpath("//li[text()='Quality Assurance']")).click();
        sleep(4000);
    }

    public static void checkFilterByDepartmentQueryResults() throws InterruptedException {
        String totalQueryResult = driver.findElement(By.xpath("//span[@class='totalResult']")).getText();
        int query= Integer.parseInt(totalQueryResult);
        int x;
        for(x=1; x<query+1; x++){
            String checkQa= driver.findElement(By.xpath("(//span[@class='position-department text-large font-weight-600 text-primary'])["+x+"]")).getText();
            assertEquals(checkQa,"Quality Assurance");
        }
    }

    public static void checkFilterByLocationQueryResults() throws InterruptedException {
        String totalQueryResult = driver.findElement(By.xpath("//span[@class='totalResult']")).getText();
        int query= Integer.parseInt(totalQueryResult);
        int x;
        for(x=1; x<query+1; x++){
            String checkQa= driver.findElement(By.xpath("(//div[@class='position-location text-large'])["+x+"]")).getText();
            assertEquals(checkQa,"Istanbul, Turkey");
        }
    }

    public static void checkApplyButtonInQueryResults() throws InterruptedException {
        String totalQueryResult = driver.findElement(By.xpath("//span[@class='totalResult']")).getText();
        int query= Integer.parseInt(totalQueryResult);
        int x;
        for(x=1; x<query+1; x++){
            driver.findElement(By.xpath("(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'])["+x+"]")).isDisplayed();
        }
    }

    public static void clickApplyNow() throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.xpath("(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'])[1]"));

        for(WebElement e : elements) {
            String url = e.getAttribute("href");
            driver.get(url);
        }
    }



}
