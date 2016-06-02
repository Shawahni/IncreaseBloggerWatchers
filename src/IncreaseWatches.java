import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class IncreaseWatches {

	WebDriver driver;
	String url = "http://technorhinos.blogspot.com";
	@Before 
	public void init() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void test() throws InterruptedException {
		driver.get(url);
		ArrayList<String>postsLinks = new ArrayList<String>();
		do {
		List<WebElement> post = driver.findElements(By.cssSelector(".post-title.entry-title>a"));
		for (WebElement webElement : post) {
			String postLink = webElement.getAttribute("href");
			postsLinks.add(postLink);
		}
		
		List<WebElement> olderPostsLink = driver.findElements(By.cssSelector("#Blog1_blog-pager-older-link"));
		if(olderPostsLink.size()>0) {
			System.out.println("Moving to older posts");
			olderPostsLink.get(0).click();
		} else {
			System.out.println("No more older posts");
			break;
		}
		} while(true);
		while(true) {
		for(String link : postsLinks) {
			driver.get(link);
			Thread.sleep(5000);
		}
		}
	}
	
	@After
	public void tearDown() {
		driver.close();
		if(driver != null) {
			driver = null;
		}
	}

}
