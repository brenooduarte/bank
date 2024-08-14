package com.accenture.academico.Clientes.domain.repository;

import com.accenture.academico.Clientes.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepositoryCustom {
    Optional<Cliente> findByCpfAndSenha(String cpf, String senha);
}
