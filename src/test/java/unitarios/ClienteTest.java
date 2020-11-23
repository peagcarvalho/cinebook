package unitarios;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import br.edu.ifpb.cinebooking.beans.ClienteBean;
import br.edu.ifpb.cinebooking.modelo.Cliente;
import br.edu.ifpb.cinebooking.modelo.Endereco;

public class ClienteTest {
	
	private ClienteBean clienteBean;
	private Cliente cliente;
	
	@Test
	public void testaCadastrar() {
		clienteBean = Mockito.mock(ClienteBean.class);
		cliente = Mockito.mock(Cliente.class);
		
		cliente.setNome("Zara");
		cliente.setCpf("222.222.222-22");
		cliente.setDataNascimento(new Date("25/04/1990"));
		cliente.setEmail("zara@gmail.com");
		cliente.setSenha("zara001");
		cliente.setEndereco(new Endereco("Recife", "PE"));
		
		clienteBean.setCliente(cliente);
		
		assertTrue(clienteBean.cadastrar());
	}

}
