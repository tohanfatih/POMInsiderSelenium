package InsiderDemo;

import Pages.ApplyJobPage;
import Pages.BlogPage;
import Pages.HomePage;
import Pages.JobSearchPage;
import javafx.scene.layout.Priority;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.*;
import static org.testng.Assert.*;

public class DemoTests {

    public static WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public static void setup(String browser) throws Exception{
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver", "src/test/java/Drivers/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(new FirefoxProfile());
            options.addPreference("dom.webnotifications.enabled", false);

            driver = new FirefoxDriver(options);
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver","src/test/java/Drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);

        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://useinsider.com/");
    }

    @Test(priority=1)
    public static void HomepageTest(){
        //assertTrue(driver.getTitle().contains("Insider personalization engine for seamless customer experiences"));

        String homePageTitle = HomePage.verifyHomePageTitle();
        Assert.assertEquals(homePageTitle, "Insider personalization engine for seamless customer experiences","Home page title not matched");

    }

    @Test(priority=2)
    public static void AcceptCookiesThenGoToBlogPage() throws Exception {

        HomePage.clickAcceptCookies();
        //driver.findElement(By.xpath("//a[@id='wt-cli-accept-all-btn']")).click(); //accept cookies
        //Thread.sleep(2000);
        HomePage.clickMore();
        //driver.findElement(By.xpath("//span[text()='More']")).click(); //click more
        sleep(2000);
        HomePage.clickCareers();
        //driver.findElement(By.xpath("//h5[text()='Careers']")).click(); //click careers
        sleep(2000);

        /*BlogPage.seeFindJob();
        BlogPage.clickFindJob();
        //driver.findElement(By.xpath("(//a[text()='Find your dream job'])[1]")).click();


        driver.findElement(By.xpath("//label[@for='filter-by-location']")).isDisplayed(); //see search item
        sleep(2000);
        driver.findElement(By.xpath("//span[@id='select2-filter-by-location-container']")).click();
        driver.findElement(By.xpath("//li[text()='Istanbul, Turkey']")).click();
        sleep(2000);


        driver.findElement(By.xpath("//span[@id='select2-filter-by-department-container']")).click();
        driver.findElement(By.xpath("//li[text()='Quality Assurance']")).click();
        sleep(4000);


        String totalQueryResult = driver.findElement(By.xpath("//span[@class='totalResult']")).getText();
        int query= Integer.parseInt(totalQueryResult);
        int x;
        for(x=1; x<query+1; x++){
        String checkQa= driver.findElement(By.xpath("(//span[@class='position-department text-large font-weight-600 text-primary'])["+x+"]")).getText();
        assertEquals(checkQa,"Quality Assurance");
        }

        for(x=1; x<query+1; x++){
            String checkQa= driver.findElement(By.xpath("(//div[@class='position-location text-large'])["+x+"]")).getText();
            assertEquals(checkQa,"Istanbul, Turkey");
        }

        for(x=1; x<query+1; x++){
            driver.findElement(By.xpath("(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'])["+x+"]")).isDisplayed();
        }

        List<WebElement> elements = driver.findElements(By.xpath("(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'])[1]"));

        for(WebElement e : elements) {
            String url = e.getAttribute("href");
            driver.get(url);
        }
        String urlCheck = driver.getCurrentUrl();
        urlCheck.contains("https://jobs.lever.co/useinsider");
        sleep(3000);*/
    }

    @Test(priority=3)
    public static void SeeBlogPageThenGoJobSearchPage() throws Exception {
        BlogPage.seeFindJob();
        BlogPage.clickFindJob();
        sleep(2000);
    }

    @Test(priority=4)
    public static void FilterJobsByLocation() throws Exception {
        JobSearchPage.jobFilterByLocation();
    }

    @Test(priority=5)
    public static void FilterJobsByDepartment() throws Exception {
        JobSearchPage.jobFilterByDepartment();
    }

    @Test(priority=6)
    public static void CheckQueryResultsForLocation() throws Exception {
        JobSearchPage.checkFilterByLocationQueryResults();
    }

    @Test(priority=7)
    public static void CheckQueryResultsForDepartment() throws Exception {
        JobSearchPage.checkFilterByDepartmentQueryResults();
    }

    @Test(priority=8)
    public static void CheckQueryResultsForApplyJob() throws Exception {
        JobSearchPage.checkApplyButtonInQueryResults();
    }

    @Test(priority=9)
    public static void ClickApplyNow() throws Exception {
        JobSearchPage.clickApplyNow();
    }

    @Test(priority=10)
    public static void CheckJobApplicationPage() throws Exception {
        ApplyJobPage.checkApplicationPage();
    }

    @AfterMethod //AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result){
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                // To create reference of TakesScreenshot
                TakesScreenshot screenshot=(TakesScreenshot)driver;
                // Call method to capture screenshot
                File src=screenshot.getScreenshotAs(OutputType.FILE);
                // Copy files to specific location
                // result.getName() will return name of test case so that screenshot name will be same as test case name
                FileUtils.copyFile(src, new File("src/test/java/screenshots/"+result.getName()+".png"));
                System.out.println("Successfully captured a screenshot");
            }catch (Exception e){
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
        //driver.quit();
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
