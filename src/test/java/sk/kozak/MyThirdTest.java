package sk.kozak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyThirdTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/registracia.php";

    @Before
    public void setUp(){
        // Chromedriver.exe path
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test(){
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//form/div[1]/input")).sendKeys("adrian@doma.sk");
        driver.findElement(By.xpath("//form/div[2]/input")).sendKeys("Adrian");
        driver.findElement(By.xpath("//form/div[3]/input")).sendKeys("Kozak");


    }

    @After
    public void tearDown(){
//        driver.quit();
    }
}