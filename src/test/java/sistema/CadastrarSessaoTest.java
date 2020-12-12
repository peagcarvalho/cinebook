package sistema;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CadastrarSessaoTest {
	
private WebDriver driver;
	
	@Before
	public void configurar() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\pedro\\novo-workspace\\cinebook\\webDriver\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	@Test
	public void testaCadastrarFilme() throws InterruptedException {
		driver.get("https://localhost:8443/cinebook/paginaInicial.xhtml");
		assertTrue(driver.getTitle().contentEquals("Página Inicial - Cinebook"));
		Thread.sleep(3000);
		
		WebElement linkLogar = driver.findElement(By.id("linkLogar"));
		linkLogar.click();
		Thread.sleep(3000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:login"));
		inputTextEmail.sendKeys("luciana@gmail.com");
		Thread.sleep(3000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("luci123");
		Thread.sleep(3000);
		
		WebElement botaoLogar = driver.findElement(By.id("form:botaoLogar"));
		botaoLogar.click();
		Thread.sleep(5000);
		
		assertTrue(driver.getTitle().contentEquals("Painel do Operador - Cinebook"));
		Thread.sleep(3000);
		
		WebElement linkCadastrarSessao = driver.findElement(By.id("linkCadastrarSessao"));
		linkCadastrarSessao.click();
		Thread.sleep(3000);
		
		assertTrue(driver.getTitle().contentEquals("Criar uma sessão - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextFilme = driver.findElement(By.id("form:filmeMenu"));
		inputTextFilme.sendKeys("Vingadores: Guerra Infinita");
		Thread.sleep(3000);
		
		WebElement inputTextSala = driver.findElement(By.id("form:sala"));
		inputTextSala.sendKeys("03");
		Thread.sleep(3000);
		
		WebElement inputTextDataExib = driver.findElement(By.id("form:data_exib"));
		inputTextDataExib.sendKeys("03/12/2020");
		Thread.sleep(3000);
		
		WebElement inputTextHoraExib = driver.findElement(By.id("form:hora_exib"));
		inputTextHoraExib.sendKeys("19:30");
		Thread.sleep(3000);
		
		WebElement inputTextQuantIngressos = driver.findElement(By.id("form:quantMaxIngressos"));
		inputTextQuantIngressos.sendKeys("65");
		Thread.sleep(3000);
		
		WebElement inputTextLegendado = driver.findElement(By.id("form:cbLegendado"));
		inputTextLegendado.click();
		Thread.sleep(3000);
		
		WebElement inputCadastrar = driver.findElement(By.id("form:botaoCadastrar"));
		inputCadastrar.click();
		Thread.sleep(3000);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

}
