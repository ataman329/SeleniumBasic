package sk.kozak;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ClickOnceTest {

  private WebDriver driver;

    @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  @Test
  public void testClickOnce() {
      String baseUrl = "http://localhost/playground/clickmebaby.php";
      driver.get(baseUrl);

    WebElement clickButton = driver.findElement(By.id("clickMe"));
    clickButton.click();
    clickButton.click();

    String clickCount = driver.findElement(By.id("clicks")).getText();
    assertEquals("2", clickCount);
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}