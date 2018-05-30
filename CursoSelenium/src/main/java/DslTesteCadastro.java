import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DslTesteCadastro {
	
	private WebDriver driver;
	
	private DSL dsl;
	
	@Before
	public void inicializa(){
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	dsl = new DSL(driver);
	
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void CadastroCompleto(){
				
		dsl.escrever("elementosForm:nome","Maria");
		//Assert.assertEquals("Maria",driver.findElement(By.name("elementosForm:nome")).getAttribute("value"));
		dsl.escrever("elementosForm:sobrenome","Eduarda Faustino");
		//Assert.assertEquals("Eduarda Faustino",driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		dsl.clicarRadio("elementosForm:sexo:0");
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		dsl.selecionarCombo("elementosForm:escolaridade","Superior");
		
		//forma de valida��o
		
		/*WebElement element = cadastro.findElement(By.name("elementosForm:escolaridade"));
	    Select combo = new Select(element);
	    List<WebElement> options = combo.getOptions();
	    Assert.assertEquals(8, options.size());
	    
	    boolean encontrou = false;
	    for (WebElement option: options){
	    	if(option.getText().equals("Superior")){
	    		encontrou = true;
	    		break;	    		   		
	    	}
	    }
	    Assert.assertTrue(encontrou);	    
	    combo.selectByVisibleText("Superior");*/
	    
	    //forma de valida��o
		
		/*WebElement element1 = cadastro.findElement(By.id("elementosForm:esportes"));
	    Select combo1 = new Select(element1);
	    combo1.selectByVisibleText("Futebol");*/
		dsl.selecionarCombo("elementosForm:esportes", "Natacao" );
		
	    
	    dsl.clicarBotao("elementosForm:cadastrar");
		//Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		//Assert.assertEquals("Ronaldo", cadastro.findElement(By.id("descNome")).getText());
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Maria"));
		Assert.assertEquals("Sobrenome: Eduarda Faustino", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
		//Assert.assertEquals("Sugestoes: ", cadastro.findElement(By.id("descSugestoes")).getText());
	 
	}
	
	  
}
