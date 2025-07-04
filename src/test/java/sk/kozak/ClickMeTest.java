package sk.kozak;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class ClickMeTest extends MainTest {

    @Before
    public void openBaseUrl(){
        driver.get(getBASE_URL() + "clickmebaby.php");
    }

    @Test
    public void test(){
        Assert.assertEquals("Inicialny pocet klikov","0", driver.findElement(By.id("clicks")).getText());
        Assert.assertEquals("klikov", driver.findElement(By.className("description")).getText());
        for (int i = 1; i < 11; i++) {
            driver.findElement(By.id("clickMe")).click();
            Assert.assertEquals(String.valueOf(i), driver.findElement(By.id("clicks")).getText());
            if (i==1){
//                sout + TAB
                System.out.println("overujem slovo klik");
                Assert.assertEquals("klik", driver.findElement(By.className("description")).getText());
            }
            if(i>2 && i<=4){
                System.out.println("overujem slovo kliky");
                Assert.assertEquals("kliky", driver.findElement(By.className("description")).getText());
            }
            if(i>4){
                System.out.println("overujem slovo klikov");
                Assert.assertEquals("klikov", driver.findElement(By.className("description")).getText());
            }
        }



    }
}