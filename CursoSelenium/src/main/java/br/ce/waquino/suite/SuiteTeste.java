package br.ce.waquino.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.waquino.page.DslCampoDeTreinamento;
import br.ce.waquino.page.DslPageParametrizacaoTestRegraCadastro;
import br.ce.waquino.test.DslTestCadastroRegraDeNegocio;

@RunWith(Suite.class)
@SuiteClasses({
	DslPageParametrizacaoTestRegraCadastro.class,
	DslCampoDeTreinamento.class,
	DslTestCadastroRegraDeNegocio.class
	
})
public class SuiteTeste {

}
