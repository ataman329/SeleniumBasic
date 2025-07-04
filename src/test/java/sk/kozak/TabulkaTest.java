package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TabulkaTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/tabulka.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void test(){
        driver.get(BASE_URL);
        int rows =Integer.parseInt(driver.findElement(By.xpath("//table/tbody/tr[last()]/td[1]")).getText());
        for (int i = 1; i < rows+1; i++) {

            Assert.assertFalse(driver.findElement(By.xpath("//table/tbody/tr[" +i+ "]/td[3]")).getText().isEmpty());
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}