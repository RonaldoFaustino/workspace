import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DslTesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa(){
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	dsl = new DSL(driver);
	page = new CampoTreinamentoPage(driver);
	
	
	}
			
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void CadastroCompleto(){
		page.setNome("Maria");
		//Assert.assertEquals("Maria",driver.findElement(By.name("elementosForm:nome")).getAttribute("value"));
		page.setSobrenome("Eduarda Faustino");
		//Assert.assertEquals("Eduarda Faustino",driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		page.setSexoMasculino();
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		page.setComidaPizza();
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		page.setEscolaridade("Superior");
		
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
		page.setEsporte("Natacao");
		
	    
	    page.setCadastrar();
		//Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		//Assert.assertEquals("Ronaldo", cadastro.findElement(By.id("descNome")).getText());
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Maria"));
		Assert.assertEquals("Sobrenome: Eduarda Faustino", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: superior", page.obterEscolaridade());
		Assert.assertEquals("Esportes: Natacao", page.obterEsporte());
		//Assert.assertEquals("Sugestoes: ", cadastro.findElement(By.id("descSugestoes")).getText());
	 
	}
	
	  
}
