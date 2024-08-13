package com.accenture.academico.OperacoesBancarias.controller;

import com.accenture.academico.OperacoesBancarias.model.Operacao;
import com.accenture.academico.OperacoesBancarias.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("operacoes")
public class OperacaoController {

    @Autowired
    OperacaoService operacaoService;

    @GetMapping("/contas/{id}")
    public ResponseEntity<List<Operacao>> listarExtratoPorConta(@PathVariable Integer id) {
        List<Operacao> operacoes = operacaoService.listarExtratoPorConta(id);
        return ResponseEntity.ok(operacoes);
    }

}
