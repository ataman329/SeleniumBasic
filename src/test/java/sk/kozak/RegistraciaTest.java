package sk.kozak;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistraciaTest extends MainTest {
    private WebDriverWait wait;
    private final String validEmail = "brano@manohy.sk";
    private final String validName = "brano";
    private final String validSurname = "peres";
    private final String validPassword = "eleonora666";


    @Before
    public void openBaseUrl(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(getBASE_URL() + "registracia.php");

    }

    @Test
    public void testMissingAllInputs() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.name("robot")));
        checkbox.click();

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-danger')]")));
        Assert.assertTrue("Error alert should be displayed", errorAlert.isDisplayed());


    }

    @Test
    public void testMissingPasswords (){
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);

        WebElement checkbox = driver.findElement(By.name("robot"));
        checkbox.click();
        System.out.println("Checkbox selected before click: " + checkbox.isSelected());
        checkbox.click();
        System.out.println("Checkbox selected after click: " + checkbox.isSelected());

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-danger')]")));
        Assert.assertTrue("Error alert should be displayed for missing passwords", errorAlert.isDisplayed());
    }

    @Test
    public void testMismatchedPasswords (){
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.name("password")).sendKeys("bludcislo1");
        driver.findElement(By.name("password-repeat")).sendKeys(validPassword);

        WebElement checkbox = driver.findElement(By.name("robot"));
        checkbox.click();

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-danger')]")));
        Assert.assertTrue("Error alert should be displayed for mismatched passwords", errorAlert.isDisplayed());
    }

    @Test
    public void testShortPassword() {
        // Test password shorter than 5 characters
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("password-repeat")).sendKeys("123");

        // Check robot checkbox
        WebElement checkbox = driver.findElement(By.name("robot"));
        checkbox.click();

        // Submit form
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verify error message
        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-danger')]")));
        Assert.assertTrue("Error alert should be displayed for short password", errorAlert.isDisplayed());
    }

    @Test
    public void testMissingRobotCheckbox () {
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("password-repeat")).sendKeys(validPassword);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-danger')]")));
        Assert.assertTrue("Error alert should be displayed for missing robot checkbox", errorAlert.isDisplayed());
    }

    @Test
    public void testSuccessfulRegistration () {
        driver.findElement(By.name("email")).sendKeys(validEmail);
        driver.findElement(By.name("name")).sendKeys(validName);
        driver.findElement(By.name("surname")).sendKeys(validSurname);
        driver.findElement(By.name("password")).sendKeys(validPassword);
        driver.findElement(By.name("password-repeat")).sendKeys(validPassword);

        WebElement checkbox = driver.findElement(By.name("robot"));
        checkbox.click();

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert-success')]")));
        Assert.assertTrue("Success alert should be displayed", successAlert.isDisplayed());

        String successText = successAlert.getText();
        Assert.assertTrue("Success message should contain 'uspesna'",
                successText.contains("uspesna"));

    }

    @Test
    public void testInputErrorBorder () {
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertTrue(
                driver.findElement(By.xpath("//div[contains(@class,'form-group') and contains(@class,'has-error')]"))
                        .isDisplayed()
        );

        Assert.assertTrue(
                driver.findElement(By.xpath("//div[contains(@class,'form-group') and contains(@class,'has-error')]"))
                        .isDisplayed());
    }
}