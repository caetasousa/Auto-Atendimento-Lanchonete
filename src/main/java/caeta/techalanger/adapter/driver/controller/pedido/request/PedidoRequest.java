package caeta.techalanger.adapter.driver.controller.pedido.request;

import java.util.ArrayList;
import java.util.List;

public class PedidoRequest {

    private String cpfCliente;
    private List<PedidoItensRequest> itens = new ArrayList<>();


    public PedidoRequest(String cpfCliente, List<PedidoItensRequest> itens) {
        this.cpfCliente = cpfCliente;
        this.itens = itens;
    }

    public PedidoRequest(List<PedidoItensRequest> itens) {
        this.itens = itens;
    }

    public String getCliente() {
        return cpfCliente;
    }

    public void setCliente(String cliente) {
        this.cpfCliente = cliente;
    }

    public List<PedidoItensRequest> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItensRequest> itens) {
        this.itens = itens;
    }
}
