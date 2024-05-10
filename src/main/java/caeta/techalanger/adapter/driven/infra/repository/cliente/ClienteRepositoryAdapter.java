package caeta.techalanger.adapter.driven.infra.repository.cliente;

import caeta.techalanger.core.application.ports.ClienteRepositoryPort;
import caeta.techalanger.core.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    @Autowired
    ClienteRepository repository;

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity novoCliente = repository.save(new ClienteEntity(cliente));
        return new Cliente(novoCliente.id, novoCliente.nome, cliente.getCpf(), cliente.getEmail());
    }

    @Override
    public Optional<Cliente> findByCPF(String cpf) {
        Optional<ClienteEntity> consulta = repository.findByCpf(cpf);

        return consulta.map(ClienteEntity::paraCliente);
    }
}
