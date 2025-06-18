package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DimensionDonaldTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost/playground/moveme.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
//        driver.manage().window().maximize();
        driver.manage().window().setPosition(new Point(500, 500));
        driver.get(BASE_URL);
        Assert.assertTrue(driver.findElement(By.xpath("//img[@id='donald']")).isDisplayed());


    }

    @After
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }
}