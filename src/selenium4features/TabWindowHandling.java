package selenium4features;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TabWindowHandling {

    public static void main(String[] args) throws InterruptedException {
        WebDriver d = new ChromeDriver();
        d.manage().window().maximize();
        d.get("https://cloud.google.com/");
        System.out.println(d.getTitle());
        d.findElement(By.xpath("//a[contains(text(),'Get started for free')]")).click();
        System.out.println(d.getTitle());

        

    }
}
