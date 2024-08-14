package com.accenture.academico.ContasBancarias;

import com.accenture.academico.ContasBancarias.domain.enums.StatusConta;
import com.accenture.academico.ContasBancarias.domain.enums.TipoConta;
import com.accenture.academico.ContasBancarias.domain.model.ContaBancaria;
import com.accenture.academico.ContasBancarias.domain.model.dto.form.ContaBancariaDTOForm;
import com.accenture.academico.ContasBancarias.domain.repository.ContaBancariaRepository;
import com.accenture.academico.ContasBancarias.domain.service.ContaBancariaService;
import com.accenture.academico.ContasBancarias.mensageria.model.MensagemOperacao;
import com.accenture.academico.ContasBancarias.mensageria.model.ValidacaoClienteEvent;
import com.accenture.academico.ContasBancarias.mensageria.producer.ContaRequestProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ContasBancariasApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private ContaBancariaRepository contaBancariaRepository;

	@Mock
	private ContaRequestProducer contaRequestProducer;

	@InjectMocks
	private ContaBancariaService contaBancariaService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testListarContasPorClienteId() {
		when(contaBancariaRepository.findByIdCliente(1)).thenReturn(List.of(new ContaBancaria()));

		List<ContaBancaria> contas = contaBancariaService.listarContasPorClienteId(1);

		assertNotNull(contas);
		assertFalse(contas.isEmpty());
	}

	@Test
	void testBuscarContaPorId() {
		ContaBancaria conta = new ContaBancaria();
		when(contaBancariaRepository.findById(1)).thenReturn(Optional.of(conta));

		Optional<ContaBancaria> foundConta = contaBancariaService.buscarContaPorId(1);

		assertTrue(foundConta.isPresent());
		assertEquals(conta, foundConta.get());
	}

	@Test
	void testConsultarSaldo() {
		ContaBancaria conta = new ContaBancaria();
		conta.setSaldo(BigDecimal.valueOf(100.00));
		when(contaBancariaRepository.findById(1)).thenReturn(Optional.of(conta));

		BigDecimal saldo = contaBancariaService.consultarSaldo(1);

		assertEquals(BigDecimal.valueOf(100.00), saldo);
	}

	@Test
	void testSolicitarDeposito() {
		ContaBancaria conta = new ContaBancaria();
		conta.setSaldo(BigDecimal.valueOf(100.00));
		when(contaBancariaRepository.findById(1)).thenReturn(Optional.of(conta));

		MensagemOperacao mensagem = new MensagemOperacao(null, BigDecimal.valueOf(50.00), 1, null);
		contaBancariaService.solicitarDeposito(mensagem);

		assertEquals(BigDecimal.valueOf(150.00), conta.getSaldo());
		verify(contaRequestProducer, times(1)).enviarOperacao(mensagem);
	}

	@Test
	void testSolicitarSaque() {
		ContaBancaria conta = new ContaBancaria();
		conta.setSaldo(BigDecimal.valueOf(100.00));
		when(contaBancariaRepository.findById(1)).thenReturn(Optional.of(conta));

		MensagemOperacao mensagem = new MensagemOperacao(null, BigDecimal.valueOf(50.00), 1, null);
		contaBancariaService.solicitarSaque(mensagem);

		assertEquals(BigDecimal.valueOf(50.00), conta.getSaldo());
		verify(contaRequestProducer, times(1)).enviarOperacao(mensagem);
	}

	@Test
	void testSolicitarTransferencia() {
		ContaBancaria contaOrigem = new ContaBancaria();
		contaOrigem.setSaldo(BigDecimal.valueOf(100.00));
		ContaBancaria contaDestino = new ContaBancaria();
		contaDestino.setSaldo(BigDecimal.valueOf(50.00));

		when(contaBancariaRepository.findById(1)).thenReturn(Optional.of(contaOrigem));
		when(contaBancariaRepository.findById(2)).thenReturn(Optional.of(contaDestino));

		MensagemOperacao mensagem = new MensagemOperacao(null, BigDecimal.valueOf(30.00), 1, 2);
		contaBancariaService.solicitarTransferencia(mensagem);

		assertEquals(BigDecimal.valueOf(70.00), contaOrigem.getSaldo());
		assertEquals(BigDecimal.valueOf(80.00), contaDestino.getSaldo());
		verify(contaRequestProducer, times(1)).enviarOperacao(mensagem);
	}

	@Test
	void testCriarConta() {
		ContaBancariaDTOForm dtoForm = new ContaBancariaDTOForm(TipoConta.CORRENTE, new BigDecimal("0.05"), 1, 1);

		ContaBancaria novaConta = new ContaBancaria();
		when(contaBancariaRepository.save(any(ContaBancaria.class))).thenReturn(novaConta);

		contaBancariaService.criarConta(dtoForm);

		verify(contaRequestProducer, times(1)).enviarValidacaoCliente(any(ValidacaoClienteEvent.class));
	}

	@Test
	void testAtualizarStatusConta() {
		ContaBancaria conta = new ContaBancaria();
		when(contaBancariaRepository.findById(1)).thenReturn(Optional.of(conta));

		contaBancariaService.atualizarStatusConta(1, StatusConta.ATIVA);

		assertEquals(StatusConta.ATIVA, conta.getStatusConta());
		verify(contaBancariaRepository, times(1)).save(conta);
	}

	@Test
	public void testAplicarJuros() {
		// Dados de teste
		LocalDate dataDepositoAntiga = LocalDate.now().minusDays(40); // Mais de 30 dias
		LocalDate dataDepositoRecente = LocalDate.now().minusDays(15); // Menos de 30 dias
		BigDecimal taxaJuros = new BigDecimal("0.005"); // 0.5%

		ContaBancaria contaAntiga = new ContaBancaria();
		contaAntiga.setTipoConta(TipoConta.POUPANCA);
		contaAntiga.setDataDeposito(dataDepositoAntiga);
		contaAntiga.setSaldo(new BigDecimal("1000"));
		contaAntiga.setTaxaJuros(taxaJuros);

		ContaBancaria contaRecente = new ContaBancaria();
		contaRecente.setTipoConta(TipoConta.POUPANCA);
		contaRecente.setDataDeposito(dataDepositoRecente);
		contaRecente.setSaldo(new BigDecimal("500"));
		contaRecente.setTaxaJuros(taxaJuros);

		List<ContaBancaria> contas = Arrays.asList(contaAntiga, contaRecente);

		// Mock do repositório
		when(contaBancariaRepository.findAll()).thenReturn(contas);

		// Executar o método
		contaBancariaService.aplicarJuros();

		// Verificar resultados
		verify(contaBancariaRepository).save(contaAntiga);
		verify(contaBancariaRepository, never()).save(contaRecente);

		// Validar saldo atualizado com delta
		BigDecimal saldoEsperado = new BigDecimal("1005.000");
		assertEquals(saldoEsperado, contaAntiga.getSaldo(), "Saldo não corresponde ao esperado");

		// Verificar que a data do depósito foi atualizada
		assertEquals(LocalDate.now(), contaAntiga.getDataDeposito());
	}

}

