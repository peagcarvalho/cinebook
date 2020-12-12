package sistema;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CadastrarClienteTest {
	
	private WebDriver driver;
	
	@Before
	public void configurar() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\pedro\\novo-workspace\\cinebook\\webDriver\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	@Test
	public void testarCadastrarCliente() throws InterruptedException {
		driver.get("http://localhost:8080/cinebook/criarConta.xhtml");
		assertTrue(driver.getTitle().contentEquals("Criar conta - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextNome = driver.findElement(By.id("form:nome"));
		inputTextNome.sendKeys("Marta");
		Thread.sleep(3000);
		
		WebElement inputTextCPF = driver.findElement(By.id("form:cpf"));
		inputTextCPF.sendKeys("111.111.111-11");
		Thread.sleep(3000);
		
		WebElement inputTextDataNasc = driver.findElement(By.id("form:data_nasc"));
		inputTextDataNasc.sendKeys("02/04/1998");
		Thread.sleep(3000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:email"));
		inputTextEmail.sendKeys("marta@gmail.com");
		Thread.sleep(3000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("marta123");
		Thread.sleep(3000);
		
		WebElement botaoCriarConta = driver.findElement(By.id("form:botaoCriarConta"));
		botaoCriarConta.click();
		Thread.sleep(5000);
		
		assertTrue(driver.getTitle().contentEquals("Entre com sua conta - Cinebook"));
		Thread.sleep(3000);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

}
