package interviewquestions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class GetTextGetInnerTextDifference {
    public static void main(String[] args) {
        /*
            getText():
            * Retrieves only the visible text displayed on the screen, which means it will not return text hidden by CSS (like display: none, visibility: hidden, etc.).
            * This method relies on the browser’s rendering of the page, so if the text is loaded dynamically (e.g., through JavaScript after the page loads), it may not always capture the content accurately, especially if that content is not yet visible.

            getAttribute("innerText"):
            * Fetches the raw text content from the HTML source within the element, regardless of visibility or styles.
            * Unlike getText(), it captures text even if it's hidden by CSS. It also works with dynamically loaded content that may not be visible immediately but has been rendered into the DOM.
         */
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        List<WebElement> anchorElements = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links available on this link is "+anchorElements.size());

        for(WebElement we : anchorElements){
            if(!we.getText().equalsIgnoreCase(we.getAttribute("innerText"))){//This loop would be entered only if getText and getAttribute("innerText") return different values
                System.out.println("Either "+we.getText()+" or "+we.getAttribute("innerText")+" is not identified");
                /*
                    If you find getText() is not returning the expected text, it could be due to one of these reasons:
                    * The text is not visible when getText() is called.
                    * JavaScript dynamically modifies the text, but it isn't immediately reflected visually.
                    In these cases, getAttribute("innerText") often works better because it pulls the text from the underlying DOM.
                 */
            }

            
        }
    }

}
