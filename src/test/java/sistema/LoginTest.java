package sistema;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class LoginTest {
	
private WebDriver driver;
	
	@Before
	public void configurar() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\pedro\\novo-workspace\\cinebook\\webDriver\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	@Test
	public void testaLogin() throws InterruptedException {
		driver.get("http://localhost:8080/cinebook/paginaInicial.xhtml");
		assertTrue(driver.getTitle().contentEquals("Página Inicial - Cinebook"));
		Thread.sleep(3000);
		
		WebElement linkLogar = driver.findElement(By.id("linkLogar"));
		linkLogar.click();
		Thread.sleep(5000);
		
		assertTrue(driver.getTitle().contentEquals("Entre com sua conta - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:login"));
		inputTextEmail.sendKeys("admin@gmail.com");
		Thread.sleep(3000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("admin001");
		Thread.sleep(3000);
		
		WebElement botaoLogar = driver.findElement(By.id("form:botaoLogar"));
		botaoLogar.click();
		Thread.sleep(5000);
		
		assertTrue(driver.getTitle().contentEquals("Painel de Administração - Cinebook"));
		Thread.sleep(5000);
		
		WebElement botaoDeslogar = driver.findElement(By.id("j_idt12:botaoDeslogar"));
		botaoDeslogar.click();
		Thread.sleep(5000);
		
		assertTrue(driver.getTitle().contentEquals("Página Inicial - Cinebook"));
		Thread.sleep(3000);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

}
