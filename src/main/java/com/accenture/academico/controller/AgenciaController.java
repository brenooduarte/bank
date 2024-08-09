package com.accenture.academico.controller;

import com.accenture.academico.model.Agencia;
import com.accenture.academico.model.Cliente;
import com.accenture.academico.model.ContaBancaria;
import com.accenture.academico.model.Endereco;
import com.accenture.academico.model.dto.view.ClienteDTO;
import com.accenture.academico.model.enums.TipoConta;
import com.accenture.academico.repository.AgenciaRepository;
import com.accenture.academico.repository.ClienteRepository;
import com.accenture.academico.repository.ContaBancariaRepository;
import com.accenture.academico.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bank")
public class AgenciaController {

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ContaBancariaRepository contaRepository;

    @GetMapping
    public List<Agencia> listarAgencias() {
        return agenciaRepository.findAll();
    }

    @GetMapping("/{idAgencia}")
    public ResponseEntity<Agencia> obterAgencia(@PathVariable Integer idAgencia) {
        Optional<Agencia> agencia = agenciaRepository.findById(idAgencia);
        return agencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{idAgencia}/clientes")
    public ResponseEntity<List<Cliente>> listarClientesPorAgencia(@PathVariable Integer idAgencia) {
        Optional<Agencia> agencia = agenciaRepository.findById(idAgencia);
        if (agencia.isPresent()) {
            List<Cliente> clientes = clienteRepository.findAllByAgencia_IdAgencia(idAgencia);
            return ResponseEntity.ok(clientes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idAgencia}/clientes/{idCliente}")
    public ResponseEntity<Cliente> obterClienteDeAgencia(@PathVariable Integer idAgencia, @PathVariable Integer idCliente) {
        Optional<Agencia> agencia = agenciaRepository.findById(idAgencia);
        if (agencia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cliente.isEmpty() || !cliente.get().getAgencia().getIdAgencia().equals(idAgencia)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente.get());
    }

    @GetMapping("/{idAgencia}/clientes/{idCliente}/contas")
    public ResponseEntity<List<ContaBancaria>> listarContasPorCliente(@PathVariable Integer idAgencia,
                                                                      @PathVariable Integer idCliente) {
        try {
            Optional<Agencia> agenciaOpt = agenciaRepository.findById(idAgencia);
            Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
            if (agenciaOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            if (clienteOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Cliente cliente = clienteOpt.get();
            List<ContaBancaria> contaBancarias = cliente.getContaBancarias();
            return ResponseEntity.ok(contaBancarias);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{idAgencia}/clientes/{idCliente}/contas/{idConta}")
    public ResponseEntity<ContaBancaria> obterContaPorId(@PathVariable Integer idAgencia, @PathVariable Integer idCliente,
                                                         @PathVariable Integer idConta) {
        try {
            Cliente cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            Optional<ContaBancaria> contaOpt = contaRepository.findById(idConta);
            if (contaOpt.isPresent()) {
                ContaBancaria contaBancaria = contaOpt.get();
                if (contaBancaria.getCliente().getId().equals(idCliente)) {
                    return ResponseEntity.ok(contaBancaria);
                } else {
                    return ResponseEntity.status(404).body(null);
                }
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Agencia> criarAgencia(@RequestBody Agencia agencia) {
        try {
            Agencia agenciaSalva = agenciaRepository.save(agencia);
            return ResponseEntity.created(URI.create("/bank/" + agenciaSalva.getIdAgencia())).body(agenciaSalva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{idAgencia}/clientes")
    public ResponseEntity<Cliente> criarCliente(@PathVariable Integer idAgencia, @RequestBody ClienteDTO dto) {
        try {
            Agencia agencia = agenciaRepository.findById(idAgencia)
                    .orElseThrow(() -> new RuntimeException("Agência não encontrada"));

            Endereco endereco = dto.getEndereco();
            enderecoRepository.save(endereco);

            Cliente cliente = new Cliente();
            cliente.setNome(dto.getNome());
            cliente.setCPF(dto.getCpf());
            cliente.setTelefone(dto.getTelefone());
            cliente.setEndereco(endereco);
            cliente.setAgencia(agencia);

            List<ContaBancaria> contaBancarias = dto.getContas().stream().map(contaDTO -> {
                ContaBancaria contaBancaria = new ContaBancaria();
                contaBancaria.setSaldo(contaDTO.getSaldo());
                contaBancaria.setTipoConta(TipoConta.valueOf(contaDTO.getTipo()));
                contaBancaria.setCliente(cliente);
                return contaBancaria;
            }).collect(Collectors.toList());

            cliente.setContaBancarias(contaBancarias);
            Cliente clienteSalvo = clienteRepository.save(cliente);
            contaRepository.saveAll(contaBancarias);
            clienteSalvo.setContaBancarias(contaRepository.findByCliente_Id(clienteSalvo.getId()));
            return ResponseEntity.created(URI.create("/bank/" + idAgencia + "/clientes/" + clienteSalvo.getId()))
                    .body(clienteSalvo);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/{idAgencia}/clientes/{idCliente}/contas")
    public ResponseEntity<ContaBancaria> criarConta(@PathVariable Integer idAgencia, @PathVariable Integer idCliente,
                                                    @RequestBody ContaBancaria contaBancaria) {
        try {
            Optional<Agencia> agenciaOpt = agenciaRepository.findById(idAgencia);
            Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);

            if (agenciaOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            if (clienteOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Cliente cliente = clienteOpt.get();
            contaBancaria.setCliente(cliente);
            ContaBancaria contaSalva = contaRepository.save(contaBancaria);
            cliente.getContaBancarias().add(contaSalva);
            clienteRepository.save(cliente);
            return ResponseEntity
                    .created(URI
                            .create("/bank/" + idAgencia + "/clientes/" + idCliente + "/conta/" + contaSalva.getNumero()))
                    .body(contaSalva);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{idAgencia}")
    public ResponseEntity<Void> deletarAgencia(@PathVariable Integer idAgencia) {
        try {
            Optional<Agencia> agenciaOpt = agenciaRepository.findById(idAgencia);
            if (agenciaOpt.isPresent()) {
                agenciaRepository.deleteById(idAgencia);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{idAgencia}/clientes/{idCliente}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer idAgencia, @PathVariable Integer idCliente) {
        try {
            Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
            if (clienteOpt.isPresent() && clienteOpt.get().getAgencia().getIdAgencia().equals(idAgencia)) {
                clienteRepository.deleteById(idCliente);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{idAgencia}/clientes/{idCliente}/contas/{idConta}")
    public ResponseEntity<Void> deletarConta(@PathVariable Integer idAgencia, @PathVariable Integer idCliente,
                                             @PathVariable Integer idConta) {
        try {
            Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
            if (clienteOpt.isPresent() && clienteOpt.get().getAgencia().getIdAgencia().equals(idAgencia)) {
                Optional<ContaBancaria> contaOpt = contaRepository.findById(idConta);
                if (contaOpt.isPresent() && contaOpt.get().getCliente().getId().equals(idCliente)) {
                    contaRepository.deleteById(idConta);
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
