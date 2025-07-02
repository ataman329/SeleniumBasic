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

import static org.junit.Assert.assertEquals;

public class SemaforTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/semafor.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        String expectedGreen = "rgba(10, 129, 0, 1)";
        String expectedRed = "rgba(205, 58, 63, 1)";
        String expectedOrange = "rgba(191, 111, 7, 1)";

        driver.get(BASE_URL);
        Actions actions = new Actions (driver);

        WebElement trafficLight = driver.findElement(By.className("light"));
        String actualRedColor = trafficLight.getCssValue("background-color");
        Assert.assertEquals(expectedRed, actualRedColor);

        actions.moveToElement(trafficLight).build().perform();

        String actualGreenColor = trafficLight.getCssValue("background-color");
        Assert.assertEquals(expectedGreen, actualGreenColor);

        actions.clickAndHold(trafficLight).build().perform();
        String actualOrange = trafficLight.getCssValue("background-color");
        assertEquals(expectedOrange, actualOrange);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}