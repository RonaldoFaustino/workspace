import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DslCampoDeTreinamento {
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
	public void testeTextField(){
		dsl.escrever("elementosForm:nome","Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", dsl.obterValorDoCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea(){
		
		dsl.escrever("elementosForm:sugestoes","TextArea");
		Assert.assertEquals("TextArea", dsl.obterValorDoCampo("elementosForm:sugestoes"));
		
	}
	
	@Test
	public void deveInteragirComRadioButton(){
		
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
	}
	// Não foi feito 
	@Test
	public void deveInteragirComCheckBox(){
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCombo(){
		
	    dsl.selecionarCombo("elementosForm:escolaridade", "Especializacao");
	    Assert.assertEquals("Especializacao", dsl.obterValorCombo("elementosForm:escolaridade"));
	    //driver.quit();
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		
	    WebElement element = driver.findElement(By.name("elementosForm:escolaridade"));
	    Select combo = new Select(element);
	    List<WebElement> options = combo.getOptions();
	    Assert.assertEquals(8, options.size());
	    
	    boolean encontrou = false;
	    for (WebElement option: options){
	    	if(option.getText().equals("Mestrado")){
	    		encontrou = true;
	    		break;
	    	}
	    }
	    Assert.assertTrue(encontrou);	    
	}
	
	@Test
	public void deveVerificarValoresComboMultiplos(){
		
	    WebElement element = driver.findElement(By.id("elementosForm:esportes"));
	    Select combo = new Select(element);
	    combo.selectByVisibleText("Natacao");
	    combo.selectByVisibleText("Karate");
	    combo.selectByVisibleText("O que eh esporte?");
	    
	    List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
	    Assert.assertEquals(3, allSelectedOptions.size());
	    
	    combo.deselectByVisibleText("Natacao");
	    allSelectedOptions = combo.getAllSelectedOptions();
	    Assert.assertEquals(2, allSelectedOptions.size());
	    
	}
	
	@Test
	public void deveInteragirComBotoes(){
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}
	
	@Test	
	public void deveInteragirComlinks(){
				
		driver.findElement(By.linkText("Voltar")).click();
		
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		//Assert.fail();
	}
	
	@Test
	
	public void deveBuscartextosNapagina(){
		
		//driver.findElement(By.tagName("body"));
		//System.out.println(driver.findElement(By.tagName("body")).getText());
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
	}
	
	@Test
	
	public void deveInteragirComAlertSimples(){
				
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", alert.getText());
		alert.accept();
		driver.findElement(By.name("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	
	public void deveInteragirComAlertConfirme(){
				
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		String texto = alerta.getText();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
		
		driver.findElement(By.id("confirm")).click();
		alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.accept();
	}
	
@Test
	
	public void deveInteragirComPront(){
		
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("1245");
		alerta.accept();
		Assert.assertEquals("Era 1245?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
		
		
 	}

	@Test
	public void CadastroCompleto(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.name("elementosForm:nome")).sendKeys("Maria");
		Assert.assertEquals("Maria",driver.findElement(By.name("elementosForm:nome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Eduarda Faustino");
		Assert.assertEquals("Eduarda Faustino",driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
		
		//forma de validação
		
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
	    
	    //forma de validação
		
		/*WebElement element1 = cadastro.findElement(By.id("elementosForm:esportes"));
	    Select combo1 = new Select(element1);
	    combo1.selectByVisibleText("Futebol");*/
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");
		
	    
	    WebElement botao = driver.findElement(By.id("elementosForm:cadastrar"));
		botao.click();
		//Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		//Assert.assertEquals("Ronaldo", cadastro.findElement(By.id("descNome")).getText());
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Maria"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Eduarda Faustino"));
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		Assert.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Pizza"));
		Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
		Assert.assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("Futebol"));
		//Assert.assertEquals("Sugestoes: ", cadastro.findElement(By.id("descSugestoes")).getText());
	    
	}
	
}


