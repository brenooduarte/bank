package com.accenture.academico.ContasBancarias.api.controller;

import com.accenture.academico.ContasBancarias.domain.model.MensagemOperacao;
import com.accenture.academico.ContasBancarias.domain.model.dto.form.ContaBancariaDTOForm;
import com.accenture.academico.ContasBancarias.domain.service.ContaBancariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contas")
public class ContaBancariaController {

    @Autowired
    ContaBancariaService contaBancariaService;

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
