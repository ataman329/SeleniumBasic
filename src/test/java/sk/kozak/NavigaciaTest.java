package sk.kozak;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;

public class NavigaciaTest {
    private WebDriver driver; // class variable
    private static final String BASE_URL = "http://localhost/playground/";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        String expectedClass = "active";
        List<String> pages = new ArrayList<>();
        pages.add("vybersi");
        pages.add("tabulka");
        pages.add("zjavenie");
        pages.add("redalert");
        pages.add("kalkulacka");

        for (String page : pages) {
            driver.get(BASE_URL + "/" + page + ".php");
            System.out.println(driver.findElement(By.xpath("//li[a/@href='" + page + ".php']"))
                    .getAttribute("class").contains(expectedClass));
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}