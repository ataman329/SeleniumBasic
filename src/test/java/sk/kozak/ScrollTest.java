package sk.kozak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ScrollTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/moveme.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException, IOException {
        Dimension windowSize = driver.manage().window().getSize();
        int maxX = windowSize.getWidth();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        WebElement donald = driver.findElement(By.id("donald"));
        Actions actions = new Actions(driver);

        int offsetX = maxX - 100;
        actions.clickAndHold(donald).moveByOffset(offsetX, 0).release().perform();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scrFile.toPath(), Path.of("C:/tmp/screenshot.png"));
    }

    @After
    public void tearDown(){

        driver.quit();
    }
}