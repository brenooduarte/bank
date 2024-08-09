package com.accenture.academico.service;

import com.accenture.academico.repository.AgenciaRepository;
import com.accenture.academico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenciaService {

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    ClienteRepository clienteRepository;

//    public List<Cliente> listarClientesPorAgencia(Integer agenciaId) {
//        return clienteRepository.findByAgenciasId(agenciaId);
//    }
}
