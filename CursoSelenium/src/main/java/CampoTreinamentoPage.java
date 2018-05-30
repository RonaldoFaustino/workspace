import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver){
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome){
		dsl.escrever("elementosForm:nome",nome);
	}
	
	public void setSobrenome(String sobrenome){
	dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
}
