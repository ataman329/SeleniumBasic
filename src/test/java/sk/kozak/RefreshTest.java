package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RefreshTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/clickmebaby.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);
        for (int i = 0; i < 5; i++) {
            driver.findElement(By.id("clickMe")).click();
        }
        Assert.assertEquals("5", driver.findElement(By.id("clicks")).getText());
        driver.navigate().refresh();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("clicks")));
        Assert.assertEquals("0", driver.findElement(By.id("clicks")).getText());

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}