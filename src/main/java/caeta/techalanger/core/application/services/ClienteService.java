package caeta.techalanger.core.application.services;

import caeta.techalanger.core.application.ports.ClienteRepositoryPort;
import caeta.techalanger.core.application.ports.ClienteServicePort;
import caeta.techalanger.core.domain.Cliente;

public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort repository;

    public ClienteService(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Cliente cadastraCliente(Cliente cliente) {
        return repository.salvar(cliente);
    }

    @Override
    public Cliente consultarClientePorCPF(String cpf) {
        return repository.findByCPF(cpf).orElseThrow();
    }
}
