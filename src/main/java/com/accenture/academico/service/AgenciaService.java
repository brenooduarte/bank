package com.accenture.academico.service;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.repository.AgenciaRepository;
import com.accenture.academico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

//    public List<Cliente> listarClientesPorAgencia(Integer agenciaId) {
//        return clienteRepository.findByAgenciasId(agenciaId);
//    }
}
