package selenium3features.codesnippets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyboardActions {

    /*
    In order to enter a pie symbol in the text field we need to hold the ALT key and press 227 on the numpad of the keyboard.
    This was not achievable using the Actions class of selenium. So for the keyboard functions we already have a Robot class in java
    and this can be done using the following code
     */
    public static void main(String[] args) throws AWTException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        WebElement searchArea = driver.findElement(By.xpath("//textarea[@title = 'Search']"));//In order to get the focus on the Search box
//        searchArea.sendKeys("π");//This is the best way to pass the value pie
//        searchArea.sendKeys("\u03C0");//This is the next best way
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ALT);
        r.keyPress(KeyEvent.VK_NUMPAD2);
        r.keyRelease(KeyEvent.VK_NUMPAD2);
        r.keyPress(KeyEvent.VK_NUMPAD2);
        r.keyRelease(KeyEvent.VK_NUMPAD2);
        r.keyPress(KeyEvent.VK_NUMPAD7);
        r.keyRelease(KeyEvent.VK_NUMPAD7);
        r.keyRelease(KeyEvent.VK_ALT);
//      Say for example you want to paste anything that is copied for which you would be doing the following
        StringSelection s = new StringSelection("This is the text which is copied in clipboard by using robot class");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
        //If we do not have the above two lines then the clipboard would not be having the text that we have written
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);

    }

}
