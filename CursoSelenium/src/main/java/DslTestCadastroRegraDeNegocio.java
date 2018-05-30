import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DslTestCadastroRegraDeNegocio {

	private WebDriver driver;
	
	private DSL dsl;
	
	@Before
	public void inicializa2(){
				
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
		
	}

	@Test
	public void DeveValidarNomeObrigatorio(){
				
		//cadastro.findElement(By.name("elementosForm:nome")).sendKeys("Maria");
		dsl.clicarBotao("elementosForm:cadastrar");		
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
		//cadastro.quit();
	}
	@Test
	public void DeveValidarSobreNomeObrigatorio(){
				
		dsl.escrever("elementosForm:nome", "Maria");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
		//cadastro.quit();
		
	}
	@Test
	public void DeveValidarSexoObrigatorio(){
				
		dsl.escrever("elementosForm:nome","Maria");
		dsl.escrever("elementosForm:sobrenome","Eduarda Faustino");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		//cadastro.quit();
		
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		dsl.escrever("elementosForm:nome", "Nome qualquer");
		dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		dsl.escrever("elementosForm:nome", "Nome qualquer");
		dsl.escrever("elementosForm:sobrenome", "Sobrenome qualquer");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
		
	}

}
