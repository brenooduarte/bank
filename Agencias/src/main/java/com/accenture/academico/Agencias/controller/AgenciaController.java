package com.accenture.academico.Agencias.controller;

import com.accenture.academico.Agencias.model.Agencia;
import com.accenture.academico.Agencias.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agencias")
public class AgenciaController {

    @Autowired
    AgenciaService agenciaService;

    @GetMapping
    public ResponseEntity<List<Agencia>> listarTodasAsAgencias() {
        List<Agencia> agencias = agenciaService.listarTodasAsAgencias();
        return ResponseEntity.ok(agencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agencia> buscarAgenciaPorId(@PathVariable Integer id) {
        return agenciaService.buscarAgenciaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Agencia> criarAgencia(@RequestBody Agencia agencia) {
        Agencia novaAgencia = agenciaService.criarAgencia(agencia);
        return ResponseEntity.ok(novaAgencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agencia> atualizarAgencia(@PathVariable Integer id, @RequestBody Agencia agencia) {
        return agenciaService.atualizarAgencia(id, agencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgencia(@PathVariable Integer id) {
        if (agenciaService.deletarAgencia(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
