package sk.kozak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CloseQuitTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost/playground/zenaalebomuz.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);
        System.out.println(driver.findElement(By.tagName("h1")).getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}