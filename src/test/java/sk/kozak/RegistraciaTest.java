package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistraciaTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/registracia.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testMissingAllInputs() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());
    }

    @Test
    public void testMissingPasswords (){
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.name("email")).sendKeys("brano@manohy.sk");
        driver.findElement(By.name("name")).sendKeys("brano");
        driver.findElement(By.name("surname")).sendKeys("peres");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());
    }

    @Test
    public void testMismatchedPasswords (){
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.name("email")).sendKeys("brano@manohy.sk");
        driver.findElement(By.name("name")).sendKeys("brano");
        driver.findElement(By.name("surname")).sendKeys("peres");
        driver.findElement(By.name("password")).sendKeys("bludcislo1");
        driver.findElement(By.name("password-repeat")).sendKeys("eleonora666");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());
    }

    @After
    public void tearDown(){

        driver.quit();
    }
}