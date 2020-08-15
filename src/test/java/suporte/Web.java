package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome(){
        //selecionando o chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        //faz o selenium esperar esse tempo quando executa esta ação
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //abrindo navegador
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
}
