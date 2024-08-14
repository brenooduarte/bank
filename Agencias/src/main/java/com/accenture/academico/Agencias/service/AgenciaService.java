package com.accenture.academico.Agencias.service;

import com.accenture.academico.Agencias.model.Agencia;
import com.accenture.academico.Agencias.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciaService {

    @Autowired
    AgenciaRepository agenciaRepository;

    public Optional<Agencia> buscarAgenciaPorId(Integer id) {
        return agenciaRepository.findById(id);
    }

    public List<Agencia> listarTodasAsAgencias() {
        return agenciaRepository.findAll();
    }

    public Agencia criarAgencia(Agencia agencia) {
        return agenciaRepository.save(agencia);
    }

    public Optional<Agencia> atualizarAgencia(Integer id, Agencia agenciaAtualizada) {
        return agenciaRepository.findById(id).map(agenciaExistente -> {
            agenciaExistente.setNome(agenciaAtualizada.getNome());
            agenciaExistente.setTelefone(agenciaAtualizada.getTelefone());
            agenciaExistente.setEndereco(agenciaAtualizada.getEndereco());

            return agenciaRepository.save(agenciaExistente);
        });
    }

    public boolean deletarAgencia(Integer id) {
        return agenciaRepository.findById(id).map(agencia -> {
            agenciaRepository.delete(agencia);
            return true;
        }).orElse(false);
    }
}
