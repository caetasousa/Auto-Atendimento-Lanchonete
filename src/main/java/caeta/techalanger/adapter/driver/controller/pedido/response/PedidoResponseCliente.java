package caeta.techalanger.adapter.driver.controller.pedido.response;

import caeta.techalanger.core.domain.Cliente;

public record PedidoResponseCliente(String nome) {

    public static PedidoResponseCliente paraDomainResponse(Cliente cliente) {
        return new PedidoResponseCliente(cliente.getNome());
    }
}