import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;
		
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}



	public void escrever(String id_campo, String texto){
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValorDoCampo (String id_campo){
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	public void clicarRadio(String id){
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String name, String valor){

	    WebElement element = driver.findElement(By.name(name));
	    Select combo = new Select(element);
	    //combo.selectByIndex(3);
	    //combo.selectByValue("superior");
	    combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String name){

	    WebElement element = driver.findElement(By.name(name));
	    Select combo = new Select(element);
	    //combo.selectByIndex(3);
	    //combo.selectByValue("superior");
	    return combo.getFirstSelectedOption().getText();
	}
}
