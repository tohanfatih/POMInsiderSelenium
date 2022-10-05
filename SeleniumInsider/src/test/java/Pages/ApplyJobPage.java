package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static InsiderDemo.DemoTests.driver;
import static java.lang.Thread.sleep;

public class ApplyJobPage {

    public static void checkApplicationPage() throws InterruptedException {
        String urlCheck = driver.getCurrentUrl();
        urlCheck.contains("https://jobs.lever.co/useinsider");
        sleep(3000);
    }

}
