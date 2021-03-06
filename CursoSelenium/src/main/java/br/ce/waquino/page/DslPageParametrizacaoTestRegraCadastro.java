package br.ce.waquino.page;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.waquino.core.DSL;


@RunWith(Parameterized.class)
public class DslPageParametrizacaoTestRegraCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List <String> comidas;
	@Parameter(value=4)
	public String [] esporte;
	@Parameter(value=5)
	public String msg;
	
	@Before
	public void inicializa(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object [][]{
			{"","","", Arrays.asList(), new String []{}, "Nome eh obrigatorio"},
			{"Maria","","", Arrays.asList(), new String []{}, "Sobrenome eh obrigatorio"},
			{"Maria","Maria Eduarda","", Arrays.asList(), new String []{}, "Sexo eh obrigatorio"},
			{"Maria","Maria Eduarda","Masculino", Arrays.asList("Carne", "Vegetariano"), new String []{}, "Tem certeza que voce eh vegetariano?"},
			{"Maria","Maria Eduarda","Masculino", Arrays.asList("Carne"), new String []{"Karate","O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidaregras(){
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")){
			page.setSexoMasculino();
		}
		if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano();	
		page.setEsporte(esporte);
		page.setCadastrar();
		System.out.print(msg);
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
		
		
	}
}
