import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class CampoTreinamento {
	
	@Test
	public void testeTextField(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de Escrita");
		Assert.assertEquals("Teste de Escrita",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComTextArea(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("TextArea");
		Assert.assertEquals("TextArea",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioButton(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCheckBox(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		driver.quit();
	}
	
	
}
