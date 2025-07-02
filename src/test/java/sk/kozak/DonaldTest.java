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

public class DonaldTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/moveme.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        WebElement donald = driver.findElement(By.id("donald"));
        WebElement finish = driver.findElement(By.id("tree"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(donald, finish).build().perform();

        WebElement expectedTitle = driver.findElement(By.xpath("//div[contains(@class,'success')]/h2"));
        Assert.assertTrue(expectedTitle.isDisplayed());
        Assert.assertEquals("HOOO HOOOOO !!!!", driver.findElement(By.xpath("//div[contains(@class,'success')]/h2")).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}