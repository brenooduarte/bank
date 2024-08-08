package com.accenture.academico.controller;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agencias")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

//    @GetMapping("/{id}/clientes")
//    public ResponseEntity<List<Cliente>> listarClientesPorAgencia(@PathVariable Integer id) {
//        List<Cliente> clientes = agenciaService.listarClientesPorAgencia(id);
//        return ResponseEntity.ok(clientes);
//    }
}