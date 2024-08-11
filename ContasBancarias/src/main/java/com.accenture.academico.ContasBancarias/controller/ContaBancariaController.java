package com.accenture.academico.ContasBancarias.controller;

import com.accenture.academico.ContasBancarias.model.dto.form.TransferenciaDTO;
import com.accenture.academico.ContasBancarias.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("contas")
public class ContaBancariaController {

    @Autowired
    ContaBancariaService contaBancariaService;

    @PostMapping("/{id}/deposito")
    public ResponseEntity<Void> realizarDeposito(@PathVariable Integer id, @RequestBody BigDecimal valor) {
        contaBancariaService.realizarDeposito(id, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<Void> realizarSaque(@PathVariable Integer id, @RequestBody BigDecimal valor) {
        contaBancariaService.realizarSaque(id, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transferencia")
    public ResponseEntity<Void> realizarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) {
        contaBancariaService.realizarTransferencia(transferenciaDTO);
        return ResponseEntity.ok().build();
    }
}
