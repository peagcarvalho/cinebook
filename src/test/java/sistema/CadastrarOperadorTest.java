package sistema;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CadastrarOperadorTest {
	
private WebDriver driver;
	
	@Before
	public void configurar() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\pedro\\novo-workspace\\cinebook\\webDriver\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	@Test
	public void cadastrarOperador() throws InterruptedException {
		driver.get("https://localhost:8443/cinebook/gerente/cadastroFuncionario.xhtml");
		
		assertTrue(driver.getTitle().contentEquals("Entre com sua conta - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextLogin = driver.findElement(By.id("form:login"));
		inputTextLogin.sendKeys("luis@gmail.com");
		Thread.sleep(2000);
		
		WebElement inputTextSenhaLogin = driver.findElement(By.id("form:senha"));
		inputTextSenhaLogin.sendKeys("luis123");
		Thread.sleep(2000);
		
		WebElement botaoLogar = driver.findElement(By.id("form:botaoLogar"));
		botaoLogar.click();
		Thread.sleep(3000);
		
		assertTrue(driver.getTitle().contentEquals("Cadastrar Funcionário - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextNome = driver.findElement(By.id("form:nome"));
		inputTextNome.sendKeys("Luciana");
		Thread.sleep(2000);
		
		WebElement inputTextDataNasc = driver.findElement(By.id("form:data_nasc"));
		inputTextDataNasc.sendKeys("12/05/1990");
		Thread.sleep(2000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:email"));
		inputTextEmail.sendKeys("luciana@gmail.com");
		Thread.sleep(2000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("luci123");
		Thread.sleep(2000);
		
		WebElement inputTextPapel = driver.findElement(By.id("form:papelMenu"));
		inputTextPapel.sendKeys("Operador");
		Thread.sleep(2000);
		
		WebElement botaoCadastrar = driver.findElement(By.id("form:botaoCadastrar"));
		botaoCadastrar.click();
		Thread.sleep(3000);
		
		assertTrue(driver.getTitle().contentEquals("Funcionários Cadastrados - Cinebook"));
		Thread.sleep(3000);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

}
