package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Tabulkav2Test {

    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/tabulka.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
//        System.out.println(rows);
        for (WebElement row : rows) {
//            System.out.println(row.findElement(By.xpath("./td[3]")).getText());
            Assert.assertFalse(row.findElement(By.xpath("./td[3]")).getText().isEmpty());

        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}