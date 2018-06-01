import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {
	
	private WebDriver driver;
	
	@Before
	public void inicializa2(){
		//System.setProperties("webdriver.gecko.driver", "/user/ronaldo.ferreira/geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		driver = new ChromeDriver();
		//WebDriver driver = new InternetExplorerDriver ();		
		//driver.manage().window().setSize(new Dimension(1200, 765));
		driver.manage().window().maximize();	
		
	}
	
	@After
	public void finaliza(){
		driver.quit();
		
	}
	
	@Test
	public void teste() {
		driver.get("https://www.google.com");
		//System.out.println(driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle());
		
		
	}

}
