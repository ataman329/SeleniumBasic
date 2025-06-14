package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NemenneTest {

    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/nemenne.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);
//        System.out.println(driver.findElement(By.xpath("//input[@value='Eva Mazikova']")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Eva Mazikova']")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("sel1")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("sel2")).isEnabled());

//        System.out.println(driver.findElement(By.id("sel2")).findElement(By.xpath("./option[1]")).isEnabled());
//        System.out.println(driver.findElement(By.id("sel2")).findElement(By.xpath("./option[2]")).isEnabled());
//        System.out.println(driver.findElement(By.id("sel2")).findElement(By.xpath("./option[3]")).isEnabled());

        Assert.assertFalse(driver.findElement(By.id("sel2")).findElement(By.xpath("./option[1]")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("sel2")).findElement(By.xpath("./option[2]")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("sel2")).findElement(By.xpath("./option[3]")).isEnabled());

        Assert.assertFalse(driver.findElement(By.xpath("//button[contains(@class,'btn-danger')]")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//button[contains(@class,'btn-success')]")).isEnabled());


        }


    @After
    public void tearDown() {
    }
}