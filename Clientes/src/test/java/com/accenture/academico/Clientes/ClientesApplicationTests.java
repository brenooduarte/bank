package com.accenture.academico.Clientes;

import com.accenture.academico.Clientes.api.controller.ClienteController;
import com.accenture.academico.Clientes.domain.model.Cliente;
import com.accenture.academico.Clientes.domain.repository.ClienteRepository;
import com.accenture.academico.Clientes.domain.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientesApplicationTests {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	@InjectMocks
	private ClienteController clienteController;

	private Cliente cliente;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		cliente = new Cliente();
		cliente.setNome("João");
		cliente.setSenha("senha123");
		cliente.setCPF("123.456.789-00");
		cliente.setTelefone("(83) 99999-9999");
		cliente.setIdAgencia(1);
	}

	@Test
	void contextLoads() {
	}

	// Tests for ClienteService

	@Test
	public void testBuscarClientePorId() {
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

		Optional<Cliente> clienteEncontrado = clienteService.buscarClientePorId(1);

		assertTrue(clienteEncontrado.isPresent());
		assertEquals("João", clienteEncontrado.get().getNome());
	}

	@Test
	public void testListarTodosOsClientes() {
		when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));

		List<Cliente> clientes = clienteService.listarTodosOsClientes();

		assertNotNull(clientes);
		assertEquals(1, clientes.size());
		assertEquals("João", clientes.get(0).getNome());
	}

	@Test
	public void testCriarCliente() {
		when(clienteRepository.save(cliente)).thenReturn(cliente);

		Cliente clienteCriado = clienteService.criarCliente(cliente);

		assertNotNull(clienteCriado);
		assertEquals("João", clienteCriado.getNome());
	}

	@Test
	public void testAtualizarCliente() {
		Cliente clienteAtualizado = new Cliente();
		clienteAtualizado.setNome("Carlos");

		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
		when(clienteRepository.save(cliente)).thenReturn(cliente);

		Optional<Cliente> clienteAtualizadoResult = clienteService.atualizarCliente(1, clienteAtualizado);

		assertTrue(clienteAtualizadoResult.isPresent());
		assertEquals("Carlos", clienteAtualizadoResult.get().getNome());
	}

	@Test
	public void testDeletarCliente() {
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

		boolean resultado = clienteService.deletarCliente(1);

		assertTrue(resultado);
		verify(clienteRepository, times(1)).delete(cliente);
	}


}
