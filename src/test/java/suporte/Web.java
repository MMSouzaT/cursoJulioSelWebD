package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String AUTOMATE_USERNAME = "mrciosouza2";
    public static final String AUTOMATE_ACCESS_KEY = "yzfGEv8WUjZxGG1ZS5yy";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

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
    //aqui é pra executar o test no BrowserStack
    public static WebDriver createBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x800");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "60.0");
        caps.setCapability("os", "Windows");

        WebDriver navegador = null;
        try{
            navegador = new RemoteWebDriver(new URL(URL),caps);
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            navegador.get("http://www.juliodelima.com.br/taskit");
        }catch (MalformedURLException e){
            System.out.println("Houveram problemas com a URL: " + e.getMessage());
        }

        return navegador;
    }
}
