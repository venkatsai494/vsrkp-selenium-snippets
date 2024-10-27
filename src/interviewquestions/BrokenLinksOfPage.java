package interviewquestions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenLinksOfPage {

    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        //driver.get("http://google.com");
        driver.get("https://github.com/venkatsai494");
        List<WebElement> anchorElements = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links available on this link is "+anchorElements.size());

        for(WebElement we : anchorElements){
            URL url = new URL(we.getAttribute("href"));//Creating a URL object by passing the url of each of the link
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //We need to open the Connection using
            httpURLConnection.setRequestMethod("HEAD");//This is an optional step which would make sure only headers are retrieved so that it is faster
            httpURLConnection.setConnectTimeout(5000);//This is used to set timeout for the requests so that they do not take more than 5 seconds
            httpURLConnection.connect();
            System.out.println(we.getText());
            if(httpURLConnection.getResponseCode()>=400)
                System.out.println(we.getAttribute("innerText")+ " link is broken");
            else if(httpURLConnection.getResponseCode() > 200)
                System.out.println(we.getAttribute("innerText")+ " link is working fine");

        }

    }
}
