package caeta.techalanger.adapter.driven.infra.repository.cliente;

import caeta.techalanger.core.application.ports.ClienteRepositoryPort;
import caeta.techalanger.core.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    @Autowired
    ClienteRepository repository;

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity novoCliente = repository.save(new ClienteEntity(cliente.getNome(), cliente.getCpf(), cliente.getEmail()));
        return new Cliente(novoCliente.id, novoCliente.nome, cliente.getCpf(), cliente.getEmail());
    }

    @Override
    public Cliente consultarCPF(String cpf) {
        ClienteEntity consulta = repository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado para o CPF: " + cpf));

        return new Cliente(consulta.getId(), consulta.getNome(), consulta.getCpf(), consulta.getEmail());
    }
}
