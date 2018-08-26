package br.ce.waquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.waquino.core.DriverFactory;

public class TestAlert {
	
	private WebDriver driver;
	
	@Before
	public void inicializa2(){
				
		
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza(){
		DriverFactory.KillDriver();
		
	}
	public void deveInteragirComBotoes(){
		
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
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
}
