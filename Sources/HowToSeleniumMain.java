import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HowToSeleniumMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "Libraries\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("user-data-dir=%UserProfile%\\AppData\\Local\\Google\\Chrome\\User Data")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait driverWait = new WebDriverWait(driver,5);

        driver.manage().window().setPosition(new Point(950,0));
        driver.manage().window().setSize(new Dimension(600,900));

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

        driver.quit();
    }
}
