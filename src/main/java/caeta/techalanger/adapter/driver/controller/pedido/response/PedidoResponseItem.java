package caeta.techalanger.adapter.driver.controller.pedido.response;

import caeta.techalanger.core.domain.Produto;

import java.math.BigDecimal;

public record PedidoResponseItem(BigDecimal precoUnitario, int quantidade, PedidoResponseProduto pedidoResponseProduto) {
}
