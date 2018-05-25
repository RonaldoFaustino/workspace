import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.Select;



public class CampoTreinamento {
	
	@Test
	public void testeTextField(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.name("elementosForm:nome")).sendKeys("Teste de Escrita");
		Assert.assertEquals("Teste de Escrita",driver.findElement(By.name("elementosForm:nome")).getAttribute("value"));
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
	
	@Test
	public void deveInteragirComCombo(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	    WebElement element = driver.findElement(By.name("elementosForm:escolaridade"));
	    Select combo = new Select(element);
	    //combo.selectByIndex(3);
	    //combo.selectByValue("superior");
	    combo.selectByVisibleText("Especializacao");
	    
	    Assert.assertEquals("Especializacao", combo.getFirstSelectedOption().getText());
	    //driver.quit();
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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
	    driver.quit();
	}
	
	@Test
	public void deveInteragirComBotoes(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		driver.quit();
	}
	
	@Test	
	public void deveInteragirComlinks(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.linkText("Voltar")).click();
		
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		//Assert.fail();
	}
	
	@Test
	
	public void deveBuscartextosNapagina(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//driver.findElement(By.tagName("body"));
		//System.out.println(driver.findElement(By.tagName("body")).getText());
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
	}
	
	@Test
	
	public void deveInteragirComAlertSimples(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", alert.getText());
		alert.accept();
		driver.findElement(By.name("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	
	public void deveInteragirComAlertConfirme(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
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
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
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
		WebDriver cadastro = new ChromeDriver();
		cadastro.manage().window().maximize();
		cadastro.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		cadastro.findElement(By.name("elementosForm:nome")).sendKeys("Ronaldo");
		Assert.assertEquals("Ronaldo",cadastro.findElement(By.name("elementosForm:nome")).getAttribute("value"));
		cadastro.findElement(By.id("elementosForm:sobrenome")).sendKeys("Oliveira Viana");
		Assert.assertEquals("Oliveira Viana",cadastro.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		cadastro.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(cadastro.findElement(By.id("elementosForm:sexo:0")).isSelected());
		cadastro.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(cadastro.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		new Select(cadastro.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
		
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
		new Select(cadastro.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");
		
	    
	    WebElement botao = cadastro.findElement(By.id("elementosForm:cadastrar"));
		botao.click();
		//Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		Assert.assertTrue(cadastro.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertEquals("Ronaldo", cadastro.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Oliveira Viana", cadastro.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Masculino", cadastro.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Pizza", cadastro.findElement(By.id("descComida")).getText());
		Assert.assertEquals("superior", cadastro.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Futebol", cadastro.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("", cadastro.findElement(By.id("descSugestoes")).getText());
	    
	    
	}
		
}
