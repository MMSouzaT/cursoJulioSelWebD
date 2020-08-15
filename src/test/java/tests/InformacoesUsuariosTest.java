package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Web;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesusuarioTestData.csv")

public class InformacoesUsuariosTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){

        navegador = Web.createChrome();
        //Clicando no botão através do texto
        WebElement formularioSignIn = navegador.findElement(By.id("signinbox"));
        //Digitar no campo com nome "login" que está dentro do formulário id"signinbox" o texto "julio0001"
        formularioSignIn.findElement(By.name("login")).sendKeys("julio0001");
        //Digitar no campo com nome "senha" que está dentro do formulário id"password" o texto "123456"
        formularioSignIn.findElement(By.name("password")).sendKeys("123456");
        //Clicando no link sing in
        navegador.findElement(By.linkText("SIGN IN")).click();
        //clicar no link com a classe "me"
        navegador.findElement(By.className("me")).click();
        // clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }


    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada){

        //clicar em um botão através do seu xpath
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        // identificar a popup onde está o formulário de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));
        //Na combo de name type escolhe a opção "phone
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);
        //no campo de name "contat" digitar "+559999999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
        // clicar no testlink "save"
        popupAddMoreData.findElement(By.linkText("SAVE")).click();
        //na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);


        //validar que dentro do elemento com class "me" está no texto  "Hi, Julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Julio", textoNoElementoMe);
    }

    @Test
     public void removerUmContatoDoUsuario(){
        //clicar no seu elemento pelo seu xpath, fallowing pega o elemento seguinte, preciding pega o anterior
        navegador.findElement(By.xpath("//span[contains(text(),\"+5511985447844\")]/following-sibling::a")).click();

        //confirmar a janela javascript
        navegador.switchTo().alert().accept();
        //validar texto Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);
        //Tirar screenshot
//        String screenshotArquivo = "C:\\Users\\marcio.souza\\IdeaProjects\\webdriver\\test-report" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
//        Screenshot.tirar(navegador, screenshotArquivo);
//        Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        //logout
    }
    @After
    public void tearDown(){
        //para fechar todas as abas usa o quit, para fechar apenas a atual usa o .close()
       // navegador.quit();
    }
}