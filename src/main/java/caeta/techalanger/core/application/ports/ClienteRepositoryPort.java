package caeta.techalanger.core.application.ports;

import caeta.techalanger.core.domain.Cliente;

import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente salvar(Cliente cliente);
    Optional<Cliente> findByCPF(String cpf);
}
