package com.accenture.academico.Agencias;

import com.accenture.academico.Agencias.model.Agencia;
import com.accenture.academico.Agencias.repository.AgenciaRepository;
import com.accenture.academico.Agencias.service.AgenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class AgenciasApplicationTests {

	@Mock
	private AgenciaRepository agenciaRepository;

	@InjectMocks
	private AgenciaService agenciaService;

	private Agencia agencia;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		agencia = new Agencia();
		agencia.setIdAgencia(1);
		agencia.setNome("Agencia Teste");
		agencia.setTelefone("123456789");
		// Endereco também pode ser setado se necessário
	}

	@Test
	void buscarAgenciaPorIdExistente() {
		when(agenciaRepository.findById(1)).thenReturn(Optional.of(agencia));

		Optional<Agencia> resultado = agenciaService.buscarAgenciaPorId(1);

		assertTrue(resultado.isPresent());
		assertEquals(agencia, resultado.get());
		verify(agenciaRepository, times(1)).findById(1);
	}

	@Test
	void buscarAgenciaPorIdInexistente() {
		when(agenciaRepository.findById(1)).thenReturn(Optional.empty());

		Optional<Agencia> resultado = agenciaService.buscarAgenciaPorId(1);

		assertFalse(resultado.isPresent());
		verify(agenciaRepository, times(1)).findById(1);
	}

	@Test
	void listarTodasAsAgencias() {
		Agencia agencia2 = new Agencia();
		agencia2.setIdAgencia(2);
		agencia2.setNome("Agencia 2");
		agencia2.setTelefone("987654321");

		when(agenciaRepository.findAll()).thenReturn(Arrays.asList(agencia, agencia2));

		List<Agencia> resultado = agenciaService.listarTodasAsAgencias();

		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(agencia));
		assertTrue(resultado.contains(agencia2));
		verify(agenciaRepository, times(1)).findAll();
	}

	@Test
	void criarAgencia() {
		when(agenciaRepository.save(agencia)).thenReturn(agencia);

		Agencia resultado = agenciaService.criarAgencia(agencia);

		assertEquals(agencia, resultado);
		verify(agenciaRepository, times(1)).save(agencia);
	}

	@Test
	void atualizarAgenciaExistente() {
		Agencia agenciaAtualizada = new Agencia();
		agenciaAtualizada.setNome("Agencia Atualizada");
		agenciaAtualizada.setTelefone("111111111");

		when(agenciaRepository.findById(1)).thenReturn(Optional.of(agencia));
		when(agenciaRepository.save(agencia)).thenReturn(agencia);

		Optional<Agencia> resultado = agenciaService.atualizarAgencia(1, agenciaAtualizada);

		assertTrue(resultado.isPresent());
		assertEquals("Agencia Atualizada", resultado.get().getNome());
		assertEquals("111111111", resultado.get().getTelefone());
		verify(agenciaRepository, times(1)).findById(1);
		verify(agenciaRepository, times(1)).save(agencia);
	}

	@Test
	void atualizarAgenciaInexistente() {
		Agencia agenciaAtualizada = new Agencia();
		agenciaAtualizada.setNome("Agencia Atualizada");
		agenciaAtualizada.setTelefone("111111111");

		when(agenciaRepository.findById(1)).thenReturn(Optional.empty());

		Optional<Agencia> resultado = agenciaService.atualizarAgencia(1, agenciaAtualizada);

		assertFalse(resultado.isPresent());
		verify(agenciaRepository, times(1)).findById(1);
		verify(agenciaRepository, never()).save(any(Agencia.class));
	}

	@Test
	void deletarAgenciaExistente() {
		when(agenciaRepository.findById(1)).thenReturn(Optional.of(agencia));

		boolean resultado = agenciaService.deletarAgencia(1);

		assertTrue(resultado);
		verify(agenciaRepository, times(1)).findById(1);
		verify(agenciaRepository, times(1)).delete(agencia);
	}

	@Test
	void deletarAgenciaInexistente() {
		when(agenciaRepository.findById(1)).thenReturn(Optional.empty());

		boolean resultado = agenciaService.deletarAgencia(1);

		assertFalse(resultado);
		verify(agenciaRepository, times(1)).findById(1);
		verify(agenciaRepository, never()).delete(any(Agencia.class));
	}
}
