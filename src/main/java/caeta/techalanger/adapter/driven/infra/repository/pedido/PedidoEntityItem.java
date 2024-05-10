package caeta.techalanger.adapter.driven.infra.repository.pedido;

import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoEntity;
import caeta.techalanger.core.domain.PedidoItem;
import jakarta.persistence.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "pedido_item")
public class PedidoEntityItem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    private int quantidade;

    @ManyToOne
    private PedidoEntity pedido;

    @ManyToOne
    private ProdutoEntity produto;

    public PedidoEntityItem() {
    }

    public PedidoEntityItem(PedidoItem item) {
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.produto = new ProdutoEntity(item.getProduto());
    }

    public PedidoItem paraItemPedido() {
        return new PedidoItem(produto.paraProduto(), quantidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }
}
