package residencia.vendas.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import residencia.vendas.entidade.Usuario;
import residencia.vendas.entidade.Item;
import residencia.vendas.entidade.Pedido;

public class PedidoBuilder {
    
    public static PedidoBuilder umPedido() {
        return new PedidoBuilder();
    }

    private Usuario usuario;
    private List<Item> itens;
    private BigDecimal custo;

    public PedidoBuilder doUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public PedidoBuilder comALista(List<Item> itens) {
        this.itens = itens;
        return this;
    }

    public PedidoBuilder custando(BigDecimal custo) {
        this.custo = custo;
        return this;
    }

    public Pedido builder() {
        return new Pedido(this.usuario, this.itens, LocalDate.now(), this.custo);
    }
}
