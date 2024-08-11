package com.accenture.academico.ContasBancarias.model.dto.view;

import java.util.List;

public record ClienteDTO(
        String nome,
        String cpf,
        String telefone,
        List<ContaBancariaDTO> contas
) {
}