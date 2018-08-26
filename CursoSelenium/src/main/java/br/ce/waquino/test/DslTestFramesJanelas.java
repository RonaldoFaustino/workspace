package br.ce.waquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.waquino.core.DSL;
import br.ce.waquino.core.DriverFactory;



public class DslTestFramesJanelas {
	
	
	private DSL dsl;

	@Before
	public void inicializa(){
		
		//driver.manage().window().setSize(new Dimension(1200, 765));
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
		DriverFactory.getDriver().quit();
	}

	@Test
	public void deveInteragirComFrames(){
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);

		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
	}
	
	@Test
	public void deveInteragirComFramesEscondidos(){
		WebElement frame =  DriverFactory.getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
	
	@Test
	public void deveInteragirComJanelas(){
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		DriverFactory.KillDriver();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo(){
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(DriverFactory.getDriver().getWindowHandle());
		System.out.println(DriverFactory.getDriver().getWindowHandles());
		dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
}
