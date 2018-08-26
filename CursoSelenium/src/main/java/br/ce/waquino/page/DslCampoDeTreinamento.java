package br.ce.waquino.page;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import br.ce.waquino.core.DSL;
import br.ce.waquino.core.DriverFactory;

public class DslCampoDeTreinamento {
	
	private DSL dsl;
	
	@Before
	public void inicializa2(){
				
		
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
		//driver.quit();
		
	}
	
	@Test
	public void testeTextField(){
		dsl.escrever("elementosForm:nome","Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDuplo(){
		dsl.escrever("elementosForm:nome", "Wagner");
		Assert.assertEquals("Wagner", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Aquino");
		Assert.assertEquals("Aquino", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea(){
		
		dsl.escrever("elementosForm:sugestoes","TextArea");
		Assert.assertEquals("TextArea", dsl.obterValorCampo("elementosForm:sugestoes"));
		
	}
	
	@Test
	public void deveInteragirComRadioButton(){
		
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		
	}
	// N�o foi feito 
	@Test
	public void deveInteragirComCheckBox(){
		
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
		
	}
	
	@Test
	public void deveInteragirComCombo(){
		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplos(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	    
	}
	
	@Test
	public void deveInteragirComBotoes(){
		
		dsl.clicarBotao("buttonSimple");
				
		WebElement botao = DriverFactory.getDriver().findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}
	
	@Test	
	public void deveInteragirComlinks(){
				
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		//Assert.fail();
	}
	
	@Test
	
	public void deveBuscartextosNapagina(){
		
		//driver.findElement(By.tagName("body"));
		//System.out.println(driver.findElement(By.tagName("body")).getText());
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	
	public void deveInteragirComAlertSimples(){
				
		DriverFactory.getDriver().findElement(By.id("alert")).click();
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", alert.getText());
		alert.accept();
		DriverFactory.getDriver().findElement(By.name("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	
	public void deveInteragirComAlertConfirme(){
				
		DriverFactory.getDriver().findElement(By.id("confirm")).click();
		Alert alerta = DriverFactory.getDriver().switchTo().alert();
		String texto = alerta.getText();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
		
		DriverFactory.getDriver().findElement(By.id("confirm")).click();
		alerta = DriverFactory.getDriver().switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.accept();
	}
	
@Test
	
	public void deveInteragirComPront(){
		
	DriverFactory.getDriver().findElement(By.id("prompt")).click();
		Alert alerta = DriverFactory.getDriver().switchTo().alert();
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
		
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		DriverFactory.getDriver().findElement(By.name("elementosForm:nome")).sendKeys("Maria");
		Assert.assertEquals("Maria",DriverFactory.getDriver().findElement(By.name("elementosForm:nome")).getAttribute("value"));
		DriverFactory.getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("Eduarda Faustino");
		Assert.assertEquals("Eduarda Faustino",DriverFactory.getDriver().findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		DriverFactory.getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected());
		DriverFactory.getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		new Select(DriverFactory.getDriver().findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
		
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
		new Select(DriverFactory.getDriver().findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");
		
	    
	    WebElement botao = DriverFactory.getDriver().findElement(By.id("elementosForm:cadastrar"));
		botao.click();
		//Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		//Assert.assertEquals("Ronaldo", cadastro.findElement(By.id("descNome")).getText());
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("descNome")).getText().endsWith("Maria"));
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("descSobrenome")).getText().endsWith("Eduarda Faustino"));
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("descComida")).getText().endsWith("Pizza"));
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
		Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("descEsportes")).getText().endsWith("Futebol"));
		//Assert.assertEquals("Sugestoes: ", cadastro.findElement(By.id("descSugestoes")).getText());
	    
	}
	
	@Test
	public void testJavaScript(){
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
	//	js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrita via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement  element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Botao", "elementosForm:tableUsuarios");
	}
	
}

