import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class HowToTestNG {
    private WebDriver       driver;
    private WebDriverWait   driverWait;
    private ChromeOptions   chromeOptions = new ChromeOptions()
            .addArguments("user-data-dir=%UserProfile%\\AppData\\Local\\Google\\Chrome\\User Data")
            .addArguments("--disable-dev-shm-usage")
            .addArguments("--no-sandbox");

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver","Libraries\\chromedriver_win32\\chromedriver.exe");
        driver      = new ChromeDriver(chromeOptions);
        driverWait  = new WebDriverWait(driver,5);

        driver.manage().window().setPosition(new Point(950,0));
        driver.manage().window().setSize(new Dimension(600,900));
    }

    @AfterSuite
    public void afterSuite(){
        driver.quit();
    }

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @DataProvider
    public Object[][] data() {
        String temp_string_int = String.valueOf(1+1);
        String temp_name= "Name";
        return new String[][] {new String[] {"Name",temp_name}, new String[] {temp_string_int,"2"}};
    }

    @Test(dataProvider = "data")
    public void BasicTest1(String[] d) {
        Assert.assertEquals(d[0], d[1]);
    }

    @Test
    public void SeleniumTest1(){
        driver.get("https://www.google.com/");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hplogo\"]")));
        driver.findElement(By.xpath(
                "//input[@aria-label=\"Search\"]")).sendKeys("LinkedIn");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"tsf\"]/div[2]/div/div[2]/div[2]/ul/li[1]/div[1]/div/span")));
        driver.findElement(By.xpath(
                "//input[@value=\"Google Search\"][@aria-label=\"Google Search\"][@name][@type=\"submit\"]"))
                .click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logo\"]/img")));

        Assert.assertTrue(driver.findElements(By.xpath("//*[@id=\"logo\"]/img")).size()>0,"false");
    }
}