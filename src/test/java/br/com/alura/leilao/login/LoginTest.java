package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.name("username")).sendKeys("fulano");
        browser.findElement(By.name("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

        browser.quit();

    }
}
