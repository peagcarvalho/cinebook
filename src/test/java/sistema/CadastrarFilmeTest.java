package sistema;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CadastrarFilmeTest {
	
	private WebDriver driver;
	
	@Before
	public void configurar() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\pedro\\novo-workspace\\cinebook\\webDriver\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	@Test
	public void testaCadastrarFilme() throws InterruptedException {
		driver.get("https://localhost:8443/cinebook/admin/cadastroFilme.xhtml");
		
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
		
		
		assertTrue(driver.getTitle().contentEquals("Cadastrar filme - Cinebook"));
		Thread.sleep(3000);
		
		WebElement inputTextTitulo = driver.findElement(By.id("form:titulo"));
		inputTextTitulo.sendKeys("Vingadores: Guerra Infinita");
		Thread.sleep(3000);
		
		WebElement inputTextCapa = driver.findElement(By.id("form:link_capa"));
		inputTextCapa.sendKeys("https://theplaylist.net/wp-content/uploads/2018/03/Hawkeye-Infinity-War-Poster.jpg");
		Thread.sleep(3000);
		
		WebElement inputTextSinopse = driver.findElement(By.id("form:sinopse"));
		inputTextSinopse.sendKeys("Os Vingadores tentam impedir que Thanos consiga as jóias do infinito");
		Thread.sleep(3000);
		
		WebElement inputTextAno = driver.findElement(By.id("form:ano_lanc"));
		inputTextAno.sendKeys("2018");
		Thread.sleep(3000);
		
		WebElement inputTextClassificacao = driver.findElement(By.id("form:classMenu"));
		inputTextClassificacao.sendKeys("Livre");
		Thread.sleep(3000);
		
		WebElement inputTextGenero = driver.findElement(By.id("form:generoMenu"));
		inputTextGenero.sendKeys("Aventura");
		Thread.sleep(3000);
		
		WebElement botaoAdicionarGenero = driver.findElement(By.id("form:botaoAdicionarGenero"));
		botaoAdicionarGenero.click();
		Thread.sleep(3000);
		
		inputTextGenero.sendKeys("Ficção Científica");
		Thread.sleep(3000);
		
		botaoAdicionarGenero.click();
		Thread.sleep(3000);
		
		WebElement botaoCadastrar = driver.findElement(By.id("form:botaoCadastrar"));
		botaoCadastrar.click();
		Thread.sleep(5000);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

}
