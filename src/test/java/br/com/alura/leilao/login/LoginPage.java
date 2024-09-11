package br.com.alura.leilao.login;

import br.com.alura.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    public static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public void fechar() {
        this.browser.quit();
    }

    public void prenncheFormularioDeLogin(String username, String password) {
        this.browser.findElement(By.name("username")).sendKeys(username);
        this.browser.findElement(By.name("password")).sendKeys(password);
    }

    public LeiloesPage efetuarLogin() {
        this.browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isLoginPage() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public String getUserName() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void navegaParaPaginaDeLeiloes() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String texto) {
        return browser.getPageSource().contains(texto);
    }

    public boolean isLoginPageError() {
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }
}
