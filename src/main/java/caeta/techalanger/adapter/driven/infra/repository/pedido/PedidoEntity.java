package caeta.techalanger.adapter.driven.infra.repository.pedido;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.core.domain.Pedido;
import caeta.techalanger.core.domain.PedidoItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoEntityItem> itens = new ArrayList<>();

    @Deprecated
    public PedidoEntity() {
    }

    public PedidoEntity(Pedido pedido) {
        this.cliente = Optional.ofNullable(pedido.getCliente()).map(ClienteEntity::new).orElse(null);
        this.itens = adicionarItem(pedido.getItens());
    }

    public List<PedidoEntityItem> adicionarItem(List<PedidoItem> listaDeItens) {
        listaDeItens.forEach(item -> {
            PedidoEntityItem pedidoEntityItem = new PedidoEntityItem(item);
            pedidoEntityItem.setPedido(this);
            this.itens.add(pedidoEntityItem);
        });
        return this.itens;
    }

    public Pedido paraPedido() {
        return new Pedido(
                this.id,
                Optional.ofNullable(this.cliente).map(ClienteEntity::paraCliente).orElse(null),
                this.itens.stream().map(PedidoEntityItem::paraItemPedido).toList()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<PedidoEntityItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoEntityItem> itens) {
        this.itens = itens;
    }

}
