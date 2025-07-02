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
    private final String validEmail = "brano@manohy.sk";
    private final String validName = "brano";
    private final String validSurname = "peres";
    private final String validPassword = "eleonora666";


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);

    }

    @Test
    public void testMissingAllInputs() {
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());
    }

    @Test
    public void testMissingPasswords (){
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());
    }

    @Test
    public void testMismatchedPasswords (){
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.name("password")).sendKeys("bludcislo1");
        driver.findElement(By.name("password-repeat")).sendKeys(validPassword);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());
    }

    @Test
    public void testMissingRobotCheckbox () {
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("password-repeat")).sendKeys(validPassword);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());
    }

    @Test
    public void testSuccessfulRegistration () {
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("password-repeat")).sendKeys(validPassword);
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-success')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-success')]")).isDisplayed());
    }

    @After
    public void tearDown(){

        driver.quit();
    }
}