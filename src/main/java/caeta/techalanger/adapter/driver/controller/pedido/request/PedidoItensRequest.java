package caeta.techalanger.adapter.driver.controller.pedido.request;

public class PedidoItensRequest {

    private Long produtoId;
    private int quantidade;

    public PedidoItensRequest(Long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
