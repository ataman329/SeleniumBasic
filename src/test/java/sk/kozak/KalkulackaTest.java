package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KalkulackaTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/kalkulacka.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void testSum() {
        checkSum("7", "3", "10");
        checkSum("50", "500", "550");
        checkSum("-5", "5", "0");
        checkSum("10", "99", "109");
        checkSum("85", "30", "115");
        checkSum("-25", "30", "5");
    }

    @Test public void testDeduct() {
        checkDeduct("5", "2", "3");
        checkDeduct("7", "3", "4");
        checkDeduct("1000", "8", "992");
    }

    @Test public void testReset(){
        enterInputs("5", "6");
        driver.findElement(By.id("count")).click();
        driver.findElement(By.id("reset")).click();

        Assert.assertEquals("First input should be empty", "", driver.findElement(By.id("firstInput")).getAttribute("value"));
        Assert.assertEquals("Second input should be empty", "", driver.findElement(By.id("secondInput")).getAttribute("value"));
        Assert.assertTrue(driver.findElement(By.id("result")).getText().isEmpty());
    }

    @Test public void testInvalidInputs(){
        enterInputs("blud1", "blud2");
        driver.findElement(By.id("count")).click();

        String firstClass = driver.findElement(By.xpath("//div[input[@id='firstInput']]")).getAttribute("class");
        Assert.assertNotNull("First input container should have a class attribute", firstClass);
        Assert.assertTrue("First input container should have 'has-error' class", firstClass.contains("has-error"));

        String secondClass = driver.findElement(By.xpath("//div[input[@id='secondInput']]")).getAttribute("class");
        Assert.assertNotNull("Second input container should have a class attribute", secondClass);
        Assert.assertTrue("Second input container should have 'has-error' class", secondClass.contains("has-error"));

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    private void enterInputs(String firstInput, String secondInput) {
        driver.findElement(By.id("firstInput")).clear();
        driver.findElement(By.id("firstInput")).sendKeys(firstInput);
        driver.findElement(By.id("secondInput")).clear();
        driver.findElement(By.id("secondInput")).sendKeys(secondInput);
    }

    private void checkSum(String firstInput, String secondInput, String expected) {
        enterInputs(firstInput, secondInput);
        driver.findElement(By.id("count")).click();
        Assert.assertEquals(expected, driver.findElement(By.id("result")).getText());
    }

    private void checkDeduct(String firstInput, String secondInput, String expected) {
        enterInputs(firstInput, secondInput);
        driver.findElement(By.id("deduct")).click();
        Assert.assertEquals(expected, driver.findElement(By.id("result")).getText());
    }
}