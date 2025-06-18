package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

public class ColourTest {

    private WebDriver driver;
    private final String BASE_URL = "http://localhost/playground/stroopeffect.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);

        String rgba = driver.findElement(By.xpath("//div[contains(@class, 'colours')]/h1[1]")).getCssValue("color");
        String hexColor = Color.fromString(rgba).asHex();
        Assert.assertEquals("#ff0e0e", hexColor);
        }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
