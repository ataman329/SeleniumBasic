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

public class TabulkaVerifyTest {  // <- rename file to match this class name

    private WebDriver driver;
    private final StringBuffer verificationErrors = new StringBuffer();
    private static final String BASE_URL = "http://localhost/playground/tabulka.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);

        // Quick check of the first cell
        Assert.assertEquals("1", driver.findElement(By.cssSelector("td")).getText());

        // Check all rows: <tr>[i]/<td>
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

        for (int i = 1; i <= rows.size(); i++) {
            try {
                String expected = String.valueOf(i);
                String actual = driver.findElement(By.xpath("//tr[" + i + "]/td")).getText();
                Assert.assertEquals("Row " + i + " mismatch", expected, actual);
            } catch (Error e) {
                verificationErrors.append(e).append("\n");
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();

        if (!verificationErrors.isEmpty()) {
            Assert.fail(verificationErrors.toString());
        }
    }
}
