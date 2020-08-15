package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage{


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
    return this;
    }

    public LoginFormPage typePassowrd(String senha){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(senha);
        return this;
    }

    public SecretaPage clickSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new SecretaPage(navegador);
    }
    //Método funcional
    public SecretaPage fazerLogin(String login, String senha){
        typeLogin(login);
        typePassowrd(senha);
        clickSignIn();

        return new SecretaPage(navegador);

    }
}
