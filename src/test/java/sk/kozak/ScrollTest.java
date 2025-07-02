package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ScrollTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/moveme.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        WebElement donald = driver.findElement(By.id("donald"));
        Actions actions = new Actions(driver);

        for (int i = 0; i < 300; i++) {
            actions.clickAndHold(donald).moveByOffset(10, 0).release().build().perform();
            Thread.sleep(1);
            
        }
    }

    @After
    public void tearDown(){

//        driver.quit();
    }
}