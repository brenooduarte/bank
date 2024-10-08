package com.accenture.academico.Clientes.api.controller;

import com.accenture.academico.Clientes.domain.model.Cliente;
import com.accenture.academico.Clientes.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/validar")
    public ResponseEntity<?> validarCliente(@RequestParam String cpf, @RequestParam String senha) {
        Optional<Cliente> cliente = clienteService.validarCliente(cpf, senha);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(401).body("CPF ou senha incorretos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id) {
        return clienteService.buscarClientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodosOsClientes() {
        List<Cliente> clientes = clienteService.listarTodosOsClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
        if (clienteService.deletarCliente(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
