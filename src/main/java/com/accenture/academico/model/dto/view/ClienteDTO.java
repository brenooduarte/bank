package com.accenture.academico.model.dto.view;

import java.util.ArrayList;
import java.util.List;

import com.accenture.academico.model.Endereco;
import lombok.Data;

@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private List<ContaBancariaDTO> contas = new ArrayList<>();

}