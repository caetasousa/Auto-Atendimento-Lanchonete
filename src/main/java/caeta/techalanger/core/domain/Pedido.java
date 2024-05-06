package caeta.techalanger.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Long id;
    private Cliente cliente;
    private List<PedidoItem> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }


    public Pedido(Long id, Cliente cliente, List<PedidoItem> itens) {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
    }

    public void adicionarItem(PedidoItem item) {
        item.setPedido(this);
        this.getItens().add(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }
}
