package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WurstTest {

    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/zenaalebomuz.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);

        driver.findElement(By.xpath("//input[@value='wurst']")).click();
        System.out.println(driver.findElement(By.xpath("//h1[@class='description text-center']")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='wurst']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value='conchita']")).isSelected());
        System.out.println(driver.findElement(By.xpath("//input[@value='wurst']")).isSelected());
        System.out.println(driver.findElement(By.xpath("//input[@value='conchita']")).isSelected());

        }


    @After
    public void tearDown() {
        driver.quit();
    }
}