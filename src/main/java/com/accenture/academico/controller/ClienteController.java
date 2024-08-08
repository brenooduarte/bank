package com.accenture.academico.controller;

import com.accenture.academico.model.ContaBancaria;
import com.accenture.academico.service.ClienteService;
import com.accenture.academico.service.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ContaBancariaService contaBancariaService;

    @Operation(summary = "Lista todas as contas bancárias de um cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de contas bancárias retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}/contas")
    public ResponseEntity<List<ContaBancaria>> listarContasPorCliente(@PathVariable Integer id) {
        List<ContaBancaria> contas = contaBancariaService.buscarContasPorCliente(id);
        return ResponseEntity.ok(contas);
    }

    @Operation(summary = "Busca uma conta bancária específica por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta bancária retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta bancária não encontrada")
    })
    @GetMapping("/contas/{id}")
    public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable Integer id) {
        return contaBancariaService.buscarContaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Consulta o saldo de uma conta bancária específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta bancária não encontrada")
    })
    @GetMapping("/contas/{id}/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable Integer id) {
        BigDecimal saldo = contaBancariaService.consultarSaldo(id);
        return ResponseEntity.ok(saldo);
    }
}
