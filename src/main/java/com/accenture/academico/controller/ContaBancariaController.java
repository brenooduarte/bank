package com.accenture.academico.controller;

import com.accenture.academico.model.dto.form.TransferenciaDTO;
import com.accenture.academico.service.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("contas")
public class ContaBancariaController {

    @Autowired
    ContaBancariaService contaBancariaService;

    @Operation(summary = "Realiza um depósito em uma conta bancária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósito realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta bancária não encontrada")
    })
    @PostMapping("/{id}/deposito")
    public ResponseEntity<Void> realizarDeposito(@PathVariable Integer id, @RequestBody BigDecimal valor) {
        contaBancariaService.realizarDeposito(id, valor);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Realiza um saque em uma conta bancária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saque realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta bancária não encontrada"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente")
    })
    @PostMapping("/{id}/saque")
    public ResponseEntity<Void> realizarSaque(@PathVariable Integer id, @RequestBody BigDecimal valor) {
        contaBancariaService.realizarSaque(id, valor);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Realiza uma transferência entre contas bancárias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta de origem ou de destino não encontrada"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente ou dados inválidos")
    })
    @PostMapping("/transferencia")
    public ResponseEntity<Void> realizarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) {
        contaBancariaService.realizarTransferencia(transferenciaDTO);
        return ResponseEntity.ok().build();
    }
}
