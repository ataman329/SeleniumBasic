package sk.kozak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class XpathTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/tabulka.php";

    @Before
    public void setUp(){
        // Chromedriver.exe path
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test(){
        driver.get(BASE_URL);
        driver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[6]/td[3]"));


    }

    @After
    public void tearDown(){
//        driver.quit();
    }
}