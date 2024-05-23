package caeta.techalanger.adapter.driver.controller.pedido.response;

import caeta.techalanger.core.domain.Categoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record PedidoResponseProduto(String nome, @Enumerated(EnumType.STRING) Categoria categoria) {
}
