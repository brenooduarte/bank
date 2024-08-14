package com.accenture.academico.ContasBancarias.api.controller;

import com.accenture.academico.ContasBancarias.domain.model.ContaBancaria;
import com.accenture.academico.ContasBancarias.domain.model.dto.form.ContaBancariaDTOForm;
import com.accenture.academico.ContasBancarias.domain.service.ContaBancariaService;
import com.accenture.academico.ContasBancarias.mensageria.model.MensagemOperacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaBancariaController {

    @Autowired
    ContaBancariaService contaBancariaService;

    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<List<ContaBancaria>> listarContasPorClienteId(@PathVariable Integer clienteId) {
        List<ContaBancaria> contas = contaBancariaService.listarContasPorClienteId(clienteId);
        return contas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(contas);
    }

    @PostMapping
    public ResponseEntity<Void> criarConta(@Valid @RequestBody ContaBancariaDTOForm contaBancariaDTOForm) {
        contaBancariaService.criarConta(contaBancariaDTOForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deposito")
    public ResponseEntity<Void> solicitarDeposito(@RequestBody MensagemOperacao mensagem) {
        contaBancariaService.solicitarDeposito(mensagem);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saque")
    public ResponseEntity<Void> solicitarSaque(@RequestBody MensagemOperacao mensagem) {
        contaBancariaService.solicitarSaque(mensagem);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transferencia")
    public ResponseEntity<Void> solicitarTransferencia(@RequestBody MensagemOperacao mensagem) {
        contaBancariaService.solicitarTransferencia(mensagem);
        return ResponseEntity.ok().build();
    }
}
