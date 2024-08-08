package com.accenture.academico.service;

import com.accenture.academico.model.Endereco;
import com.accenture.academico.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Optional<Endereco> buscarEnderecoPorId(Integer id) {
        return enderecoRepository.findById(id);
    }
}
