package com.accenture.academico.controller;

import com.accenture.academico.model.Operacao;
import com.accenture.academico.service.OperacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bank/extratos")
public class OperacaoController {

    @Autowired
    OperacaoService operacaoService;

    @Operation(summary = "Lista todo o extrato de uma conta bancária por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operações listadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta bancária não encontrada")
    })
    @GetMapping("/contas/{id}")
    public ResponseEntity<List<Operacao>> listarExtratoPorConta(@PathVariable Integer id) {
        List<Operacao> operacoes = operacaoService.listarExtratoPorConta(id);
        return ResponseEntity.ok(operacoes);
    }
}
