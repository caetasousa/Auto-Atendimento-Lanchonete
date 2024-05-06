package caeta.techalanger.adapter.driven.infra.repository.pedido;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.core.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
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

    public PedidoEntity() {
    }

    public PedidoEntity(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(PedidoEntityItem item) {
        item.setPedido(this);
        this.itens.add(item);
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
