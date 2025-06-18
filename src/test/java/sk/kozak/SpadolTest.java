package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpadolTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/vybersi.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);
        new Select(driver.findElement(By.className("form-control"))).selectByVisibleText("Pikachu");
        String actualTitle = driver.findElement(By.xpath("//div[contains(@class, 'pokemon')]/h3")).getText();
        Assert.assertTrue((actualTitle.contains("Pikachu")));
    }

    @After
    public void tearDown() {
        System.out.println("predsa som tu");
        try {
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenShot.toPath(), Path.of("C://tmp//screenshot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();

        }
    }
}