package com.accenture.academico.model.dto.view;

import com.accenture.academico.model.Endereco;

import java.util.List;

public record ClienteDTO(
        String nome,
        String cpf,
        String telefone,
        Endereco endereco,
        List<ContaBancariaDTO> contas
) {
}