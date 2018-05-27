import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestCadastroRegraDeNegocio {
	
	private WebDriver cadastro;
	
	@Before
	public void inicializa2(){
				
		cadastro = new ChromeDriver();
		cadastro.manage().window().maximize();
		cadastro.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza(){
		cadastro.quit();
		
	}

	@Test
	public void DeveValidarNomeObrigatorio(){
				
		//cadastro.findElement(By.name("elementosForm:nome")).sendKeys("Maria");
		cadastro.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = cadastro.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		//cadastro.quit();
	}
	@Test
	public void DeveValidarSobreNomeObrigatorio(){
				
		cadastro.findElement(By.name("elementosForm:nome")).sendKeys("Maria");
		cadastro.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = cadastro.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		//cadastro.quit();
		
	}
	@Test
	public void DeveValidarSexoObrigatorio(){
				
		cadastro.findElement(By.name("elementosForm:nome")).sendKeys("Maria");
		cadastro.findElement(By.id("elementosForm:sobrenome")).sendKeys("Eduarda Faustino");
		cadastro.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = cadastro.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		//cadastro.quit();
		
	}
	
	@Test
	public void DeveValidarComidaObrigatorio(){
				
		cadastro.findElement(By.name("elementosForm:nome")).sendKeys("Maria");
		cadastro.findElement(By.id("elementosForm:sobrenome")).sendKeys("Eduarda Faustino");
		cadastro.findElement(By.id("elementosForm:sexo:0")).click();
		cadastro.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		cadastro.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		cadastro.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = cadastro.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		//cadastro.quit();
		
	}
}
