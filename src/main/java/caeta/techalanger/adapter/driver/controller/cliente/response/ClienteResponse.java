package caeta.techalanger.adapter.driver.controller.cliente.response;

import caeta.techalanger.core.domain.Cliente;

public record ClienteResponse(Long id, String nome, String cpf, String email) {

    public static ClienteResponse paraDomainResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(),cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }
}
