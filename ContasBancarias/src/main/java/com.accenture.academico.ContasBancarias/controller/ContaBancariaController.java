package com.accenture.academico.ContasBancarias.controller;

import com.accenture.academico.ContasBancarias.model.MensagemOperacao;
import com.accenture.academico.ContasBancarias.service.ContaBancariaService;
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
    public ResponseEntity<Void> criarConta(@RequestBody MensagemOperacao mensagem) {
        contaBancariaService.criarConta(mensagem);
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
