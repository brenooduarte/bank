package com.accenture.academico.Clientes.domain.service;

import com.accenture.academico.Clientes.domain.model.Cliente;
import com.accenture.academico.Clientes.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Optional<Cliente> buscarClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarTodosOsClientes() {
        return clienteRepository.findAll();
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> atualizarCliente(Integer id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id).map(clienteExistente -> {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setTelefone(clienteAtualizado.getTelefone());

            return clienteRepository.save(clienteExistente);
        });
    }

    public boolean deletarCliente(Integer id) {
        return clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return true;
        }).orElse(false);
    }

    public Optional<Cliente> validarCliente(String cpf, String senha) {
        return clienteRepository.findByCpfAndSenha(cpf, senha);
    }
}
