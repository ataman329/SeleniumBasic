package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class RedAlertTest {

    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/redalert.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);

//        driver.findElement(By.id("alert1")).click();
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
//        System.out.println(driver.findElement(By.xpath("//div[@class='result']/h1")).getText());

//        driver.findElement(By.id("alert2")).click();
//        Alert alert = driver.switchTo().alert();
//        System.out.println(alert.getText());
//        Assert.assertEquals("Second alert!", alert.getText());
//        alert.dismiss();
//        Assert.assertEquals("Negative", driver.findElement(By.xpath("//div[@class='result']/h1")).getText());
//        System.out.println(driver.findElement(By.xpath("//div[@class='result']/h1")).getText());

        driver.findElement(By.id("alert3")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Yuri");
        alert.accept();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result']/h1")).getText().contains("Yuri"));
        }


    @After
    public void tearDown() {
        driver.quit();
    }
}