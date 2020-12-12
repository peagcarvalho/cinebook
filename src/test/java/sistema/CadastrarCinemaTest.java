package sistema;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CadastrarCinemaTest {
	
	private WebDriver driver;
	
	@Before
	public void configurar() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\pedro\\novo-workspace\\cinebook\\webDriver\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	@Test
	public void testaCadastrarCinema() throws InterruptedException {
		driver.get("http://localhost:8080/cinebook/admin/cadastroCinema.xhtml");
		assertTrue(driver.getTitle().contentEquals("Entre com sua conta - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:login"));
		inputTextEmail.sendKeys("admin@gmail.com");
		Thread.sleep(2000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("admin001");
		Thread.sleep(2000);
		
		WebElement botaoLogar = driver.findElement(By.id("form:botaoLogar"));
		botaoLogar.click();
		Thread.sleep(3000);
		
		assertTrue(driver.getTitle().contentEquals("Cadastrar cinema - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextNome = driver.findElement(By.id("form:nome"));
		inputTextNome.sendKeys("Cinepipoca");
		Thread.sleep(2000);
		
		WebElement inputTextTelefone = driver.findElement(By.id("form:telefone"));
		inputTextTelefone.sendKeys("38411061");
		Thread.sleep(2000);
		
		WebElement inputTextLogradouro = driver.findElement(By.id("form:logradouro"));
		inputTextLogradouro.sendKeys("Rua A");
		Thread.sleep(2000);
		
		WebElement inputTextNumero = driver.findElement(By.id("form:numero"));
		inputTextNumero.sendKeys("135");
		Thread.sleep(2000);
		
		WebElement inputTextBairro = driver.findElement(By.id("form:bairro"));
		inputTextBairro.sendKeys("Centro");
		Thread.sleep(2000);
		
		WebElement inputTextCidade = driver.findElement(By.id("form:cidade"));
		inputTextCidade.sendKeys("Sertânia");
		Thread.sleep(2000);
		
		WebElement inputTextEstado = driver.findElement(By.id("form:estado"));
		inputTextEstado.sendKeys("PE");
		Thread.sleep(2000);
		
		WebElement botaoCadastrar = driver.findElement(By.id("form:botaoCadastrar"));
		botaoCadastrar.click();
		Thread.sleep(3000);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

}
