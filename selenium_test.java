import java.io.IOException;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.By; 
import org.openqa.selenium.WebElement;

public class selenium_test {
 
        public static void main(String[] args) throws IOException, InterruptedException {
                System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
 
                WebDriver driver = new ChromeDriver(chromeOptions);
 
                driver.get("http://192.168.1.240/simple-lamp/index.php");
 
                Thread.sleep(1000);
		
		login(driver);
		screenshot(driver);

                driver.quit();
        }

	public static void login(WebDriver driver) {

		WebElement searchbar = driver.findElement(By.id("username"));
                searchbar.sendKeys("michelle");
                driver.findElement(By.cssSelector("input[type='submit']")).click();

                System.out.println("testing searchbar");

                if (driver.getPageSource().contains("Logout")) {
                        System.out.println("Pass");
                } else {
                        System.out.println("Fail");
                }
 
	}

	public static void screenshot(WebDriver driver) {
	
		System.out.println("testing screenshot");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(src, new File("screenshot.png"));

		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
}
