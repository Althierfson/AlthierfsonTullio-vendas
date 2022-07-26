package residencia.vendas.builder;

import java.math.BigDecimal;

import residencia.vendas.entidade.Item;
import residencia.vendas.entidade.TipoItem;

public class ItemBuilder {
    
    public static ItemBuilder umItem() {
        return new ItemBuilder();
    }

    private BigDecimal preco;
    private BigDecimal peso;
    private TipoItem tipo;

    public ItemBuilder custando(BigDecimal custo) {
        this.preco = custo;
        return this;
    }

    public ItemBuilder pesando(BigDecimal peso) {
        this.peso = peso;
        return this;
    }

    public ItemBuilder doTipo(TipoItem tipo) {
        this.tipo = tipo;
        return this;
    }

    public Item build() {
        return new Item("Nome do Item", "Descrição do Item", this.preco, this.peso, this.tipo);
    }
}
