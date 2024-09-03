package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach(){
       this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
       this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        this.paginaDeLogin.prenncheFormularioDeLogin("fulano", "pass");
        this.paginaDeLogin.enviarFormulario();

        Assertions.assertFalse(paginaDeLogin.isLoginPage());
        Assertions.assertEquals("fulano", paginaDeLogin.getUserName());
    }

    @Test //Testing correct username and wrong password
    public void testeLoginInvalido(){
        this.paginaDeLogin.prenncheFormularioDeLogin("fulano", "wrong-pass");
        this.paginaDeLogin.enviarFormulario();

        Assertions.assertTrue(paginaDeLogin.isLoginPageError());
        Assertions.assertNull(paginaDeLogin.getUserName());
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    //Testing wrong username and correct password
    //Testing empty fields

    @Test
    public void naoDeveEntrarNaPaginaRestritaSemEstarLogado(){
        this.paginaDeLogin.navegaParaPaginaDeLeiloes();

        Assertions.assertTrue(paginaDeLogin.isLoginPage());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }
}
